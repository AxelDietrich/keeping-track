package com.keepingtrack.backend.repository;

import com.keepingtrack.backend.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.name = ?1")
    Account getAccountByName(String name);

}
