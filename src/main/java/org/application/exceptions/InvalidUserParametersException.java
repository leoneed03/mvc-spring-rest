package org.application.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidUserParametersException extends Exception {
    public InvalidUserParametersException(String message) {
        super(message);
    }
}
