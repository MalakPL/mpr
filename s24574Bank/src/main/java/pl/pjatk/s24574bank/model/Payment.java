package pl.pjatk.s24574bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@With
public class Payment {
    private final int accountId;
    private float amount;
}
