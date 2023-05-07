package com.example;

import com.example.model.Book;
import com.example.model.Category;
import com.example.model.Rating;
import com.example.model.User;
import com.example.repository.BookRepository;
import com.example.repository.CategoryRepository;
import com.example.repository.RatingRepository;
import com.example.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);

        var bookRepo = ctx.getBean(BookRepository.class);
        var categoryRepo = ctx.getBean(CategoryRepository.class);
        var ratingRepo = ctx.getBean(RatingRepository.class);
        var userRepo = ctx.getBean(UserRepository.class);

        var cat1 = new Category(null, "cat1", "red", null);
        var cat2 = new Category(null, "cat2", "green", null);
        var cat3 = new Category(null, "cat3", "blue", null);
        var cat4 = new Category(null, "cat4", "yellow", null);
        categoryRepo.saveAll(List.of(cat1, cat2, cat3, cat4));

        var book1 = new Book(null, "book1", "author1", Set.of(cat1, cat2, cat3, cat4), null);
        var book2 = new Book(null, "book2", "author1", Set.of(cat1, cat2), null);
        var book3 = new Book(null, "book3", "author1", Set.of(cat3, cat4), null);
        var book4 = new Book(null, "book4", "author1", Set.of(cat2, cat3), null);
        bookRepo.saveAll(List.of(book1, book2, book3, book4));

        var user1 = new User(null, "user1@gmail.com");
        var user2 = new User(null, "user2@gmail.com");
        var user3 = new User(null, "user3@gmail.com");
        var user4 = new User(null, "user4@gmail.com");

        userRepo.saveAll(List.of(user1, user2, user3, user4));

        var rating1 = new Rating(null, 5.0, "good", book1, user1);
        var rating2 = new Rating(null, 1.0, "bad", book1, user2);
        var rating3 = new Rating(null, 5.0, "good", book2, user1);
        var rating4 = new Rating(null, 5.0, "good", book3, user3);
        var rating5 = new Rating(null, 5.0, "good", book3, user4);
        var rating6 = new Rating(null, 5.0, "good", book3, user2);
        ratingRepo.saveAll(List.of(rating1, rating2, rating3, rating4, rating5, rating6));

        bookRepo.findAllByAuthor("author1").forEach(book -> {
            System.out.println(book.getCategories().size());
//            System.out.println(book.getRatings().size());
        });

        bookRepo.findFirst10ByAuthor("author1").forEach(book -> {
//            System.out.println(book.getCategories().size());
            System.out.println(book.getRatings().size());
        });

        bookRepo.findFirst15ByAuthor("author1").forEach(book -> {
            System.out.println(book.getCategories().size());
            System.out.println(book.getRatings().size());
        });
        bookRepo.findFirst20ByAuthor("author1").forEach(book -> {
            System.out.println(book.getCategories().size());
            System.out.println(book.getRatings().size());
        });


        bookRepo.findFirst25ByAuthor("author1").forEach(book -> {
            System.out.println(book.getCategories().size());
            System.out.println(book.getRatings().size());
            book.getRatings().forEach(rating -> {
                System.out.println(rating.getUser().getEmail());
            });
        });

        bookRepo.findAllEager().forEach(book -> {
            System.out.println(book.getCategories().size());
            System.out.println(book.getRatings().size());
            book.getRatings().forEach(rating -> {
                System.out.println(rating.getUser().getEmail());
            });
        });
    }

}
