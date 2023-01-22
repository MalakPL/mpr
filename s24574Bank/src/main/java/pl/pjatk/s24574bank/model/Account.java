package pl.pjatk.s24574bank.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import java.text.DecimalFormat;

@Data
@AllArgsConstructor
@With
public class Account {
    private int id;
    private final String firstName;
    private final String lastName;

    /* BigDecimal powinien byc.. */
    private float amount;
}
