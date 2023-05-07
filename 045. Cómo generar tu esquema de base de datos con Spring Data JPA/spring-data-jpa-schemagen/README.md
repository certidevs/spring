
# Spring Data JPA: Generación del esquema de base de datos

1. JPA

persistence.xml

javax.persistence.sql-load-script-source
jakarta.persistence.sql-load-script-source


2. HIBERNATE

spring.jpa.hibernate.ddl-auto=create
import.sql
spring.jpa.properties.hibernate.hbm2ddl.import_files=import_employees.sql


3. SPRING DATA JPA

spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true

schema.sql
data.sql

schema-h2.sql
data-h2.sql

schema-mysql.sql
data-mysql.sql

spring.sql.init.schema-locations=classpath:db/schema-mysql.sql
spring.sql.init.data-locations=classpath:db/data-mysql.sql