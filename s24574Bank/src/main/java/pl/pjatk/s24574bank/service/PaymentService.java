package pl.pjatk.s24574bank.service;

import org.springframework.stereotype.Service;
import pl.pjatk.s24574bank.exception.DatabaseException;
import pl.pjatk.s24574bank.exception.ValidationException;
import pl.pjatk.s24574bank.model.Account;
import pl.pjatk.s24574bank.model.Payment;
import pl.pjatk.s24574bank.repository.AccountRepository;

@Service
public class PaymentService {
    private final AccountRepository accountRepository;

    public PaymentService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /* Wypłacanie */
    public boolean payoff(Payment payment) throws Exception {

        /* VALIDATE */
        if (payment.getAccountId() <= 0) {
            throw new ValidationException("Account ID is wrong!");
        }

        if (payment.getAmount() <= 0) {
            throw new ValidationException("Payment is wrong!");
        }

        var optAccount = this.accountRepository.findById(payment.getAccountId());
        if (optAccount.isEmpty())
        {
            throw new DatabaseException("Account not exists!");
        }

        Account account = optAccount.get();

        if (payment.getAmount() > account.getAmount()) {
            return false;
        }

        accountRepository.setAmount(account, account.getAmount() - payment.getAmount());
        return true;
    }

    /* Wpłacanie */
    public boolean payon(Payment payment) throws Exception {

        /* VALIDATE */
        if (payment.getAccountId() <= 0) {
            throw new ValidationException("Account ID is wrong!");
        }

        if (payment.getAmount() <= 0) {
            throw new ValidationException("Payment is wrong!");
        }

        var optAccount = this.accountRepository.findById(payment.getAccountId());
        if (optAccount.isEmpty())
        {
            throw new DatabaseException("Account not exists!");
        }

        Account account = optAccount.get();
        accountRepository.setAmount(account, account.getAmount() + payment.getAmount());

        return true;
    }

}
