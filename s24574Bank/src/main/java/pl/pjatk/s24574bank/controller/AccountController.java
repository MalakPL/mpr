package pl.pjatk.s24574bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.s24574bank.exception.ValidationException;
import pl.pjatk.s24574bank.model.Account;
import pl.pjatk.s24574bank.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService)
    {
        this.accountService = accountService;
    }


    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        try {
            accountService.create(account);

        } catch (ValidationException validationException) {
            return ResponseEntity.badRequest().build();
        } catch (Exception databaseException) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable int id) {
        return accountService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
