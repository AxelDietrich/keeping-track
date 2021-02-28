package com.keepingtrack.backend.service;

import com.keepingtrack.backend.entities.Account;
import com.keepingtrack.backend.entities.Category;
import com.keepingtrack.backend.exception.DatabaseException;
import com.keepingtrack.backend.exception.RecordNotFoundException;
import com.keepingtrack.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category getById(Long id) throws RecordNotFoundException {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            return category.get();
        } else {
            throw new RecordNotFoundException("No category found for the given id " + id);
        }
    }

    public Category getCategoryByAccountIdAndCategoryName(Account account, String name) throws DatabaseException {

        Category category = categoryRepository.getCategoryByAccountIdAndCategoryName(account, name);
        if (category != null) {
            return category;
        } else {
            throw new DatabaseException("No category found for account id = " + account.getId() + " and category name = " + name);
        }
    }
}
