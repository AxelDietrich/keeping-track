package com.keepingtrack.backend.controller;

import com.keepingtrack.backend.entities.Account;
import com.keepingtrack.backend.exception.AlreadyExistsException;
import com.keepingtrack.backend.request.RequestCommonName;
import com.keepingtrack.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {

        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
//        return new Response<List<Account>>(accounts, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/account")
    public ResponseEntity addAccount(@RequestBody RequestCommonName request) throws AlreadyExistsException {

        accountService.createAccount(request.getName());
        return ResponseEntity.ok("Account successfully created");
    }
}
