package com.keepingtrack.backend.service;

import com.keepingtrack.backend.entities.Balance;
import com.keepingtrack.backend.entities.Record;
import com.keepingtrack.backend.exception.RecordNotFoundException;
import com.keepingtrack.backend.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
}
