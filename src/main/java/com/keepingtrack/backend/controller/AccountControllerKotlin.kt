package com.keepingtrack.backend.controller

import com.keepingtrack.backend.entities.Account
import com.keepingtrack.backend.service.AccountService
import com.keepingtrack.backend.request.RequestCommonName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.xml.ws.Response

@RestController
@RequestMapping("/kotlin")
class AccountControllerKotlin(@Autowired private val accountService: AccountService) {

    @GetMapping("/accounts")
    fun getAllAccounts() : ResponseEntity<List<Account>> {

        var accounts : List<Account> = accountService.allAccounts;
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/account")
    fun addAccount(@RequestBody request : RequestCommonName) : ResponseEntity<String> {
        accountService.createAccount(request.name)
        return ResponseEntity.ok("Account succesfully created")
    }

    fun fahrenheitToCelsius(temperature : Double) : Double {
        return ((temperature - 32) * (5/9))
    }

}