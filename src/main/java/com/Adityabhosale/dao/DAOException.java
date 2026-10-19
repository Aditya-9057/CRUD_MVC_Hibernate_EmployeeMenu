package com.Adityabhosale.dao;

import org.hibernate.exception.ConstraintViolationException;

public class DAOException extends RuntimeException {
    public DAOException(String message, ConstraintViolationException cve) {

        super(message);
    }
    DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
