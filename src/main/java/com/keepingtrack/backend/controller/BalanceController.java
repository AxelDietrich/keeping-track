package com.keepingtrack.backend.controller;

import com.keepingtrack.backend.entities.Balance;
import com.keepingtrack.backend.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    @PutMapping("/balance/{balanceId}")
    public ResponseEntity moveAvailableToSavings(@PathVariable Long balanceId, @RequestParam Double amount) throws Exception {
        return ResponseEntity.ok(balanceService.moveAvailableToSavings(balanceId, amount));
    }
}
