package com.keepingtrack.backend.controller;

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
    public ResponseEntity moveAvailableToSavings(@PathVariable Long balanceId, @RequestParam Double amountToSavings) throws Exception {
        return ResponseEntity.ok(balanceService.moveAvailableToSavings(balanceId, amountToSavings));
    }

    @PutMapping("/balance/savings/{balanceId}")
    public ResponseEntity moveSavingsToAvailable(@PathVariable Long balanceId, @RequestParam Double amountToAvailable) throws Exception {
        return ResponseEntity.ok(balanceService.moveSavingsToAvailable(balanceId, amountToAvailable));
    }
}
