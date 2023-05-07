package com.example.config;

import com.example.model.Customer;
import com.example.processor.CustomerProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private static final Logger LOG = LoggerFactory.getLogger(BatchConfig.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Value("${app.file.input}")
    private String customersFile;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // lectura del customers.csv
    @Bean
    public FlatFileItemReader<Customer> reader() {
        return new FlatFileItemReaderBuilder<Customer>()
                .name("customerItemReader")
                .resource(new ClassPathResource(customersFile))
                .delimited()
                .names("first_name", "last_name", "email", "gender", "ip_address")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Customer.class);
                }})
                .linesToSkip(1) // salta la primera linea del csv que es la cabecera
                .build();
    }

    // carga de datos en la tabla customers
    @Bean
    public JdbcBatchItemWriter<Customer> writer(){
        return new JdbcBatchItemWriterBuilder<Customer>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("""
                        INSERT INTO customers
                        (first_name, last_name, email, gender, ip_address)
                        VALUES
                        (:firstName, :lastName, :email, :gender, :ipAddress)
                        """)
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job job1(){
        return jobBuilderFactory.get("job1")
                .incrementer(new RunIdIncrementer())
//                .listener(customerListener())
                .start(step1())
                .next(step2())
                .next(step3())
                .build();
    }

    // Borrar los datos de la tabla customers
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    LOG.info("Borrando los datos de la tabla customers");
                    jdbcTemplate.update("DELETE FROM customers");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    CustomerProcessor processor() {
        return new CustomerProcessor();
    }
    // leer customers e insertar en la tabla customers
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<Customer, Customer> chunk(10)
                .reader(reader()) // leer csv
                .processor(processor()) // procesar
                .writer(writer()) // guardar en db
                .build();
    }
    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step2")
                .<Customer, Customer> chunk(10)
                .reader(reader()) // leer csv
                .processor(processor()) // procesar
                .writer(writer()) // guardar en db
                .build();
    }

}
