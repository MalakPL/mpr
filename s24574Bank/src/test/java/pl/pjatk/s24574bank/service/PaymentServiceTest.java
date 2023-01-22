package pl.pjatk.s24574bank.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.pjatk.s24574bank.exception.DatabaseException;
import pl.pjatk.s24574bank.model.Account;
import pl.pjatk.s24574bank.model.Payment;
import pl.pjatk.s24574bank.repository.AccountRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PaymentServiceTest {

    private static final AccountRepository accountRepository = mock(AccountRepository.class);
    private static final PaymentService paymentService = new PaymentService(accountRepository);


    @Test
    void ShouldNotCreatePayoff() {
        Payment payment = new Payment(1,10.2f);
        assertThrows(DatabaseException.class, () -> paymentService.payoff(payment));
    }

    @Test
    void ShouldNotCreatePayon() {
        Payment payment = new Payment(1,120.2f);
        assertThrows(DatabaseException.class, () -> paymentService.payon(payment));
    }


}