package com.zac4j.bookstore.rest;

import com.zac4j.bookstore.model.Account;
import com.zac4j.bookstore.model.AccountRepository;
import com.zac4j.bookstore.model.Bookmark;
import com.zac4j.bookstore.model.BookmarkRepository;
import java.net.URI;
import java.util.Collection;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/{userId}/bookmarks")
public class BookmarkRestController {

  private final BookmarkRepository bookmarkRepository;
  private final AccountRepository accountRepository;

  @Autowired BookmarkRestController(BookmarkRepository bookmarkRepository,
      AccountRepository accountRepository) {
    this.bookmarkRepository = bookmarkRepository;
    this.accountRepository = accountRepository;
  }

  @RequestMapping(method = RequestMethod.GET)
  Collection<Bookmark> readBookmarks(@PathVariable String userId) {
    this.validateUser(userId);
    return this.bookmarkRepository.findByAccountUsername(userId);
  }

  @RequestMapping(method = RequestMethod.POST)
  ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input) {
    this.validateUser(userId);

    return this.accountRepository
        .findByUsername(userId)
        .map(account -> {
          Bookmark result =
              bookmarkRepository.save(new Bookmark(account, input.uri, input.description));

          URI location = ServletUriComponentsBuilder
              .fromCurrentRequest().path("/{id}")
              .buildAndExpand(result.getId()).toUri();

          return ResponseEntity.created(location).build();
        }).orElse(ResponseEntity.noContent().build());
  }
}
