package com.keepingtrack.backend.service;

import com.keepingtrack.backend.entities.Account;
import com.keepingtrack.backend.entities.Balance;
import com.keepingtrack.backend.entities.Category;
import com.keepingtrack.backend.exception.AlreadyExistsException;
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

    //TODO check method for utility and necessity.
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

                throw new RecordNotFoundException("No account found for the given id " + accountParam.getId());
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

    public Account createAccount(String name) throws AlreadyExistsException {

        Account checkAccount = accountRepository.getAccountByName(name);
        if (checkAccount == null) {
            Account account = new Account();
            account.setName(name);
            Category categoryIncome = new Category();
            categoryIncome.setName("Ingresos");
            categoryIncome.setIncome(true);
            categoryIncome.setAccount(account);
            Category categoryDebt = new Category();
            categoryDebt.setName("Deudas");
            categoryDebt.setIncome(false);
            categoryDebt.setAccount(account);
            Category categorySavings = new Category();
            categorySavings.setName("Ahorro");
            categorySavings.setIncome(true);
            categorySavings.setAccount(account);
            Category categoryExpenses = new Category();
            categoryExpenses.setName("Gastos");
            categoryExpenses.setIncome(false);
            categoryExpenses.setAccount(account);
            List<Category> categories = new ArrayList<>();
            categories.add(categoryIncome);
            categories.add(categoryDebt);
            categories.add(categoryExpenses);
            categories.add(categorySavings);
            account.setCategories(categories);
            Balance balance = new Balance();
            balance.setDebt(0);
            balance.setAvailableAmount(0);
            balance.setSavingsAmount(0);
            balance.setAccount(account);
            account.setBalance(balance);

            account = accountRepository.save(account);
            return account;
        } else {
            throw new AlreadyExistsException("There is already an account with the name " + name);
        }
    }
}
