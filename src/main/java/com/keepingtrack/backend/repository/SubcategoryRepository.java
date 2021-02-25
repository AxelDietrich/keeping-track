package com.keepingtrack.backend.repository;

import com.keepingtrack.backend.entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}
