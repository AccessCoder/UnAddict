package de.unaddict.backend.exceptions;

import javax.security.sasl.AuthenticationException;

public class UserAlreadyExistException extends AuthenticationException {

    public UserAlreadyExistException(final String msg) {
        super(msg);
    }

}
