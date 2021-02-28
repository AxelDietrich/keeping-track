package com.keepingtrack.backend.repository;

import com.keepingtrack.backend.entities.Account;
import com.keepingtrack.backend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.account = ?1 AND c.name = ?2")
    Category getCategoryByAccountIdAndCategoryName(Account account, String name);
}
