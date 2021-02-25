package com.keepingtrack.backend.repository;

import com.keepingtrack.backend.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
