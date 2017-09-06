package com.seed.app.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Created by MEHMET on 6.9.2017.
 */
@Getter
@Setter
@AllArgsConstructor
public class EmployeeException extends Exception {
    private final HttpStatus httpStatus;
    private final String eMessage;
}
