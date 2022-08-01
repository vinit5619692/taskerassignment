package com.vinit5619692.tasker.customExceptions;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -8460356990632230194L;

    /**
     *
     * @param message
     * @param cause
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message
     */
    public BusinessException(String message) {
        super(message);
    }



}
