package com.zac4j.bookstore.model;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// Handle tedious database interactions.
public interface AccountRepository extends JpaRepository<Account, Long> {
  // Basically, create a JPA query of the form 'select a from Account a where a.username = :username'
  Optional<Account> findByUsername(String username);
}
