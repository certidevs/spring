package com.example;

import com.example.cascadeorphan.Author;
import com.example.cascadeorphan.AuthorRepository;
import com.example.cascadeorphan.Book;
import com.example.cascadeorphan.BookRepository;
import com.example.sqldelete.Employee;
import com.example.sqldelete.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        // @SQLDelete y @Where
        var employeeRepo = ctx.getBean(EmployeeRepository.class);
        employeeRepo.saveAll(List.of(
                new Employee("John"),
                new Employee("Jane"),
                new Employee("John"),
                new Employee("Jane")
        ));

        System.out.println(employeeRepo.findAll().size());
        employeeRepo.deleteById(2L);
        System.out.println(employeeRepo.findAll().size());


        // cascade = CascadeType.REMOVE
        var authorRepo = ctx.getBean(AuthorRepository.class);
        var bookRepo = ctx.getBean(BookRepository.class);

        Author author1 = new Author(null, "author1");
        Author author2 = new Author(null, "author2");
        Author author3 = new Author(null, "author3");
        authorRepo.saveAll(List.of(author1, author2, author3));

        Book book1 = new Book(null, "book1", "editorial", author1);
        Book book2 = new Book(null, "book2", "editorial", author1);
        Book book3 = new Book(null, "book3", "editorial", author1);
        Book book4 = new Book(null, "book4", "editorial", author2);
        Book book5 = new Book(null, "book5", "editorial", author2);
        Book book6 = new Book(null, "book6", "editorial", author3);
        Book book7 = new Book(null, "book7", "editorial", author3);
        bookRepo.saveAll(List.of(book1, book2, book3, book4, book5, book6, book7));

        // Opción 1 - desasociar manualmente author de book y guardar book
        // Opción 2 - cascade = CascadeType.REMOVE
//        authorRepo.deleteById(1L);



        // DESASOCIAR MANUALMENTE

        // no es owner por tanto no desasocia
//        authorRepo.findById(1L).ifPresent(author -> {
//            author.getBooks().removeIf(book -> book.getId() == 2L);
////            author.getBooks().remove(author.getBooks().iterator().next());
//            authorRepo.save(author);
//        });
        // si es owner
//        bookRepo.findByAuthorId(1L).forEach(book -> {
//            book.setAuthor(null);
//            bookRepo.save(book);
//        });

        // DESASOCIAR/ ELIMINAR MANUALMENTE orphanRemoval = true y cascade.ALL
//        authorRepo.findById(2L).ifPresent(author -> {
//            author.getBooks().remove(author.getBooks().iterator().next());
//            authorRepo.save(author);
//        });


        // Desde repositorio
        bookRepo.deleteByAuthorIdQuery(1L);

    }

}
