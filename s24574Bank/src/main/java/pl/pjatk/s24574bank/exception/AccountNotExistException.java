package pl.pjatk.s24574bank.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountNotExistException extends RuntimeException {
    private final String message;
    private final Exception exception;
}
