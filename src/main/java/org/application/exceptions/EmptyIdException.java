package org.application.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmptyIdException extends Exception {
    public EmptyIdException(String message) {
        super(message);
    }
}
