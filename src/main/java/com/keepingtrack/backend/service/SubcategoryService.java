package com.keepingtrack.backend.service;

import com.keepingtrack.backend.entities.Balance;
import com.keepingtrack.backend.entities.Record;
import com.keepingtrack.backend.entities.Subcategory;
import com.keepingtrack.backend.exception.AlreadyExistsException;
import com.keepingtrack.backend.exception.RecordNotFoundException;
import com.keepingtrack.backend.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubcategoryService {

    @Autowired
    SubcategoryRepository subcategoryRepository;

    @Autowired
    BalanceService balanceService;

    public Subcategory getSubcategoryById(Long id) throws RecordNotFoundException {
        Optional<Subcategory> subcategory = subcategoryRepository.findById(id);

        if (subcategory.isPresent()) {
            return subcategory.get();
        } else {
            throw new RecordNotFoundException("No subcategory found for given id " + id);
        }
    }

    public Subcategory updateSubcategoryAmount(Long id, double amount) throws RecordNotFoundException {

        Subcategory subcategory = this.getSubcategoryById(id);

        subcategory.setAmount(subcategory.getAmount() + amount);

        subcategory = subcategoryRepository.save(subcategory);
        Balance balance = balanceService.getBalanceById(subcategory.getCategory().getAccount().getBalance().getId());
        double currentAmount = balance.getAvailableAmount();
        if (subcategory.getCategory().isIncome()) {
            balance.setAvailableAmount(currentAmount + amount);
        } else {
            if (currentAmount - amount >= 0 ) {
                balance.setAvailableAmount(currentAmount - amount);
            } else {
                balance.setAvailableAmount(0);
                balance.setDebt(balance.getDebt() + (amount - currentAmount));
            }
        }
        balanceService.updateBalance(balance);
        return subcategory;
    }

    public Subcategory updateSubcategoryName(Long id, String name) throws RecordNotFoundException {
        Subcategory subcategory = this.getSubcategoryById(id);

        subcategory.setName(name);
        subcategory = subcategoryRepository.save(subcategory);

        return subcategory;
    }

    public Subcategory createSubcategory(Subcategory subcategoryParam) throws AlreadyExistsException {

        subcategoryParam.setId(null);
        Subcategory sub = subcategoryRepository.findByNameAndCategory(subcategoryParam.getName(), subcategoryParam.getCategory());
        if (sub == null) {
            subcategoryParam = subcategoryRepository.save(subcategoryParam);
        } else {
            throw new AlreadyExistsException("The subcategory " + subcategoryParam.getName() + " already exists");
        }

        return subcategoryParam;
    }
}
