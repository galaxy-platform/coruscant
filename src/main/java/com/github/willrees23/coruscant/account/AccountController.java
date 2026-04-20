package com.github.willrees23.coruscant.account;

import io.github.galaxyplatform.account.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Account> list() {
        return service.all();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Account> get(@PathVariable UUID uuid) {
        return service.byId(uuid)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Account create(@RequestBody Account account) {
        return service.save(account);
    }
}
