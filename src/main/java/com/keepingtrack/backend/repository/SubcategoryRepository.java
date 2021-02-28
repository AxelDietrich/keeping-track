package com.keepingtrack.backend.repository;

import com.keepingtrack.backend.entities.Category;
import com.keepingtrack.backend.entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    @Query("SELECT s FROM Subcategory s WHERE s.name = ?1 AND s.category = ?2")
    Subcategory findByNameAndCategory(String name, Category category);

    @Query("SELECT s FROM Subcategory s INNER JOIN Category c ON s.category = c WHERE s.name = ?1 and c.name = ?2")
    Subcategory findByNameAndCategoryName(String subcategoryName, String categoryName);

}
