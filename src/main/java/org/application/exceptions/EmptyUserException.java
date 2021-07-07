package org.application.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmptyUserException extends Exception {
    public EmptyUserException(String message) {
        super(message);
    }
}
