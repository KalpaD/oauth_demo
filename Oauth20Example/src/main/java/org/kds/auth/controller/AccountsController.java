package org.kds.auth.controller;

import org.kds.auth.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping("/account")
    public ResponseEntity<Account> getAccount(@RequestParam(value = "principalId") String principal) {
        Account account = new Account();
        account.setId("001");
        account.setName("Amy Fara Fowler");
        account.setId("TD");
        return ResponseEntity.ok(account);
    }
}
