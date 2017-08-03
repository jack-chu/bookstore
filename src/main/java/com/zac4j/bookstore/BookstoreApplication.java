package com.zac4j.bookstore;

import com.zac4j.bookstore.model.Account;
import com.zac4j.bookstore.model.AccountRepository;
import com.zac4j.bookstore.model.Bookmark;
import com.zac4j.bookstore.model.BookmarkRepository;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

  // Entry point
  public static void main(String[] args) {
    SpringApplication.run(BookstoreApplication.class, args);
  }

  @Bean CommandLineRunner init(AccountRepository accountRepository,
      BookmarkRepository bookmarkRepository) {
    return args -> Arrays.asList(
        "jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
        .forEach(
            a -> {
              Account account = accountRepository.save(new Account(a,
                  "password"));
              bookmarkRepository.save(new Bookmark(account,
                  "http://bookmark.com/1/" + a, "A description"));
              bookmarkRepository.save(new Bookmark(account,
                  "http://bookmark.com/2/" + a, "A description"));
            });
  }
}
