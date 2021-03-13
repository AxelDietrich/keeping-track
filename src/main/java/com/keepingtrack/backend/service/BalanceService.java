package com.keepingtrack.backend.service;

import com.keepingtrack.backend.entities.Balance;
import com.keepingtrack.backend.exception.RecordNotFoundException;
import com.keepingtrack.backend.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BalanceService {

    @Autowired
    BalanceRepository balanceRepository;

    public Balance getBalanceById(Long id) throws RecordNotFoundException {
        Optional<Balance> balance = balanceRepository.findById(id);

        if (balance.isPresent()) {
            return balance.get();
        } else {
            throw new RecordNotFoundException("No Balance found for given id " + id);
        }
    }

    public Balance updateBalance(Balance balanceParam) throws RecordNotFoundException {

        if (balanceParam.getId() != null) {
            Optional<Balance> balance = balanceRepository.findById(balanceParam.getId());

            if (balance.isPresent()) {
                Balance editBalance = balance.get();
                editBalance.setAvailableAmount(balanceParam.getAvailableAmount());
                editBalance.setDebt(balanceParam.getDebt());
                editBalance.setSavingsAmount(balanceParam.getSavingsAmount());

                editBalance = balanceRepository.save(editBalance);

                return editBalance;
            } else {

                throw new RecordNotFoundException("No Balance found for given id " + balanceParam.getId());

            }
        } else {
            throw new RecordNotFoundException("No Balance found for given id " + balanceParam.getId() );

        }
    }

    public Balance moveAvailableToSavings(Long id, Double amount) throws Exception {

        Balance balance = this.getBalanceById(id);
        double availableMoney = balance.getAvailableAmount();
        if (availableMoney - amount > 0) {
            balance.setAvailableAmount(availableMoney - amount);
            balance.setSavingsAmount(balance.getSavingsAmount() + amount);
            return balanceRepository.save(balance);
        } else {
            throw new Exception("There's no enough available money to move the requested amount to savings");
        }
    }

    public Balance moveSavingsToAvailable(Long id, Double amount) throws Exception {

        Balance balance = this.getBalanceById(id);
        double savingsAmount = balance.getSavingsAmount();
        if (savingsAmount - amount > 0) {
            balance.setAvailableAmount(balance.getAvailableAmount() + amount);
            balance.setSavingsAmount(balance.getSavingsAmount() - amount);
            return balanceRepository.save(balance);
        } else {
            throw new Exception("There's no enough savings funds to move the requested amount to available money");
        }
    }
}
