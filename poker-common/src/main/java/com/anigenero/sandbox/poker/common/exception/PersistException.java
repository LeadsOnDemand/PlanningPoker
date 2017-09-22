package com.anigenero.sandbox.poker.common.exception;

public class PersistException extends RuntimeException implements PokerError {

    private final ErrorCode error;

    /**
     * Create a persistence exception
     *
     * @param error {@link ErrorCode}
     */
    public PersistException(final ErrorCode error) {
        this.error = error;
    }

    /**
     * Create a persistence exception with a cause
     *
     * @param error {@link ErrorCode}
     * @param cause {@link Throwable}
     */
    public PersistException(final ErrorCode error, final Throwable cause) {
        super(cause);
        this.error = error;
    }

    /**
     * Gets the error code that this was thrown with
     *
     * @return {@link ErrorCode}
     */
    @Override
    public ErrorCode getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return this.error.getMessage();
    }

}
