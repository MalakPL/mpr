package pl.pjatk.s24574bank.service;

import org.springframework.stereotype.Service;
import pl.pjatk.s24574bank.exception.DatabaseException;
import pl.pjatk.s24574bank.exception.ValidationException;
import pl.pjatk.s24574bank.model.Account;
import pl.pjatk.s24574bank.repository.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }









    public void create(Account account) throws Exception
    {
        /* VALIDATE */
        if (account.getFirstName().isBlank()) {
            throw new ValidationException("First name is required!");
        }
        if (account.getLastName().isBlank()) {
            throw new ValidationException("Last name is required!");
        }

        if (accountRepository.findById(account.getId()).isPresent())
        {
            throw new ValidationException("Account with already exists!");
        }

        /* ADD TO REPO */
        try {
            accountRepository.append(account);
        } catch (Exception e) {
            throw new DatabaseException("Account already exist");
        }
    }

    public Optional<Account> findById(int id) {
        return accountRepository.findById(id);
    }

    public void removeAll() {
        accountRepository.removeAll();
    }

}
