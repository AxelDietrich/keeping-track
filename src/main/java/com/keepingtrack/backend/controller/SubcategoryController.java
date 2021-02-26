package com.keepingtrack.backend.controller;

import com.keepingtrack.backend.entities.Record;
import com.keepingtrack.backend.entities.Subcategory;
import com.keepingtrack.backend.exception.AlreadyExistsException;
import com.keepingtrack.backend.exception.RecordNotFoundException;
import com.keepingtrack.backend.request.RequestAddSubcategory;
import com.keepingtrack.backend.service.CategoryService;
import com.keepingtrack.backend.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class SubcategoryController {

    @Autowired
    SubcategoryService subcategoryService;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/{categoryId}/subcategory")
    public ResponseEntity addSubcategory(@PathVariable Long categoryId, @RequestBody RequestAddSubcategory request) throws AlreadyExistsException, RecordNotFoundException {
        Subcategory subcategory = new Subcategory();
        subcategory.setName(request.getName());
        subcategory.setCategory(categoryService.getById(categoryId));
        subcategory.setAmount(0);
        subcategory.setRecords(new ArrayList<Record>());
        subcategoryService.createSubcategory(subcategory);

        return ResponseEntity.ok("Subcategory created succesfully");
    }
}
