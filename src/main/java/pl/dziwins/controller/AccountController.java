package pl.dziwins.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dziwins.model.Account;
import pl.dziwins.model.AccountRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountRepository repository;

    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    ResponseEntity<Account> createAccount(@RequestBody @Valid Account toCreate) {
        Account result = repository.save(toCreate);
        return ResponseEntity.created((URI.create("/" + result.getId()))).body(result);
    }

    @GetMapping
    ResponseEntity<List<Account>> readAllAccounts() {
        return ResponseEntity.ok(repository.findAll());
    }
}
