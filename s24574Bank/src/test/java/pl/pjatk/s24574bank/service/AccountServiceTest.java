package pl.pjatk.s24574bank.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.pjatk.s24574bank.exception.DatabaseException;
import pl.pjatk.s24574bank.exception.ValidationException;
import pl.pjatk.s24574bank.model.Account;
import pl.pjatk.s24574bank.repository.AccountRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class AccountServiceTest {

   static AccountRepository accountRepository = mock(AccountRepository.class);
   static AccountService accountService = new AccountService(accountRepository);


    @Test
    void shouldCreateNewAccount() {
        Account account = new Account(0,"Karol", "Szmajda", 10);
        assertDoesNotThrow(() -> accountService.create(account));
    }

    @Test
    void shouldNotCreateNewAccount() {
        Account account = new Account(0,"", "Szmajda", 10);
        assertThrows(ValidationException.class, () -> accountService.create(account));
    }
}