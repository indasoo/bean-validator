package com.ginda.common.validation;

/**
 * The base exception for the Validator Framework.  All other
 * <code>Exception</code>s thrown during calls to
 * <code>Validator.validate()</code> are considered errors.
 */
public class ValidatorException extends Exception {

    private static final long serialVersionUID = 1025759372615616964L;

    /**
     * Constructs an Exception with no specified detail message.
     */
    public ValidatorException() {
        super();
    }

    /**
     * Constructs an Exception with the specified detail message.
     *
     * @param    message The error message.
     */
    public ValidatorException(String message) {
        super(message);
    }
}
