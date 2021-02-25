package com.keepingtrack.backend.controller;

import com.keepingtrack.backend.entities.Account;
import com.keepingtrack.backend.repository.AccountRepository;
import com.keepingtrack.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
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
}
