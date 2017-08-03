package com.zac4j.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Account {

  @OneToMany(mappedBy = "account")
  private Set<Bookmark> bookmarks = new HashSet<>();

  @Id
  @GeneratedValue
  private Long id;

  public Set<Bookmark> getBookmarks() {
    return bookmarks;
  }

  public Long getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  @JsonIgnore
  public String password;
  public String username;

  public Account(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Account() { // jpa only
  }
}
