package pl.pjatk.s24574bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.s24574bank.exception.ValidationException;
import pl.pjatk.s24574bank.model.Payment;
import pl.pjatk.s24574bank.model.PaymentResponse;
import pl.pjatk.s24574bank.model.PaymentStatus;
import pl.pjatk.s24574bank.service.AccountService;
import pl.pjatk.s24574bank.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final AccountService accountService;

    public PaymentController(PaymentService paymentService, AccountService accountService)
    {
        this.paymentService = paymentService;
        this.accountService = accountService;
    }

    @PostMapping("/payoff")
    public ResponseEntity<PaymentResponse> createPayoff(@RequestBody Payment payment)
    {
        var paymentResponse = new PaymentResponse(payment.getAccountId(), 0, PaymentStatus.DECLINED, "");

        try
        {
            if (paymentService.payoff(payment))
            {
                var optAccount = accountService.findById(payment.getAccountId());
                if (optAccount.isPresent())
                {
                    paymentResponse.setAmount(optAccount.get().getAmount());
                    paymentResponse.setStatus(PaymentStatus.ACCEPTED);

                }
            } else {
                paymentResponse.setMessage("You dont have enough money");
            }
        }
        catch (Exception ex)
        {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(paymentResponse);
    }

    @PostMapping("/payon")
    public ResponseEntity<PaymentResponse> createPayon(@RequestBody Payment payment)
    {
        var paymentResponse = new PaymentResponse(payment.getAccountId(), 0, PaymentStatus.DECLINED, "");

        try
        {
            if (paymentService.payon(payment))
            {
                var optAccount = accountService.findById(payment.getAccountId());
                if (optAccount.isPresent())
                {
                    paymentResponse.setAmount(optAccount.get().getAmount());
                    paymentResponse.setStatus(PaymentStatus.ACCEPTED);
                }
            }
        }
        catch (Exception ex)
        {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(paymentResponse);
    }
}
