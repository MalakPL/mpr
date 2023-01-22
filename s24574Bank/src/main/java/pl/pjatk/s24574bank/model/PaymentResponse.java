package pl.pjatk.s24574bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@Data
@AllArgsConstructor
@With
public class PaymentResponse {
    private int accountId;
    private float amount;
    private PaymentStatus status;
    private String message;
}
