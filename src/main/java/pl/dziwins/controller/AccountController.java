package pl.dziwins.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dziwins.model.Account;
import pl.dziwins.model.AccountRepository;
import pl.dziwins.model.Transaction;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Currency;
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

    @GetMapping("/{id}")
    ResponseEntity<Account> readById(@PathVariable int id){
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/byName/{name}")
    ResponseEntity<Account> readByName(@PathVariable String name){
        return repository.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/byCurrency/{currency}")
    ResponseEntity<List<Account>> readByCurrency(@PathVariable Currency currency){
        return ResponseEntity.ok(repository.findByCurrency(currency));
    }

     @GetMapping("/byTreasury/{isTreasury}")
    ResponseEntity<List<Account>> readByTreasury(@PathVariable boolean isTreasury){
        return ResponseEntity.ok(repository.findByIsTreasury(isTreasury));
    }

    @Transactional
    @PutMapping("/{id}")
    ResponseEntity<?> updateAccount(@PathVariable int id, @RequestBody @Valid Account toUpdate) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(account -> {
                    account.updateFrom(toUpdate);
                    repository.save(account);
                });
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/transfer")
    ResponseEntity<?> updateAccounts(@RequestBody @Valid Transaction transaction) {
        if ((!repository.existsById(transaction.getIdFrom())) || (!repository.existsById(transaction.getIdTo()))) {
            return ResponseEntity.notFound().build();
        }
        if (repository.findById(transaction.getIdFrom()).get().getCurrency() != repository.findById(transaction.getIdTo()).get().getCurrency()){
            return ResponseEntity.badRequest().build();
        }
        if (repository.findById(transaction.getIdFrom()).get().getMoney().isLessThan(transaction.getMoney()) && !repository.findById(transaction.getIdFrom()).get().isTreasury()){
            return ResponseEntity.badRequest().build();
        }
        repository.findById(transaction.getIdFrom())
                .ifPresent(account -> {
                    account.transferFrom(transaction.getMoney());
                    repository.save(account);
                });
        repository.findById(transaction.getIdTo())
                .ifPresent(account -> {
                    account.transferTo(transaction.getMoney());
                    repository.save(account);
                });
        return ResponseEntity.noContent().build();
    }
}
