package pl.pjatk.s24574bank.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DatabaseException extends RuntimeException {
    private final String message;
}
