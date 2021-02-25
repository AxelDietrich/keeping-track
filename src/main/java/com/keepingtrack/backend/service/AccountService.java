package com.keepingtrack.backend.service;

import com.keepingtrack.backend.entities.Account;
import com.keepingtrack.backend.entities.Record;
import com.keepingtrack.backend.exception.RecordNotFoundException;
import com.keepingtrack.backend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        List<Account> accountList = accountRepository.findAll();

        if (accountList.size() > 0) {
            return accountList;
        } else {
            return new ArrayList<Account>();
        }
    }

    public Account getAccountById(Long id) throws RecordNotFoundException {

       Optional<Account> account = accountRepository.findById(id);

       if (account.isPresent()) {
           return account.get();
       } else {
           throw new RecordNotFoundException("No account exists for given id" + id);
       }
    }

    public Account createOrUpdateAccount(Account accountParam) throws RecordNotFoundException {

        // If account exists, update
        if (accountParam.getId() != null) {
            Optional<Account> account = accountRepository.findById(accountParam.getId());

            if (account.isPresent()) {
                Account editAccount = account.get();
                editAccount.setBalance(accountParam.getBalance());
                editAccount.setCategories(accountParam.getCategories());
                editAccount.setName(accountParam.getName());

                editAccount = accountRepository.save(editAccount);

                return editAccount;

            //Account doesn't exists
            } else {

                accountParam = accountRepository.save(accountParam);

                return accountParam;
            }
        } else {
            accountParam = accountRepository.save(accountParam);
            return accountParam;
        }
    }

    public void deleteAccountById(Long id) throws RecordNotFoundException {

        Optional<Account> account = accountRepository.findById(id);

        if (account.isPresent()) {
            accountRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No account found for given id" + id);
        }
    }
}
