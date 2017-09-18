package com.anigenero.sandbox.poker.common.exception;

public class ServerException extends RuntimeException implements PokerError {

    private final ErrorCode error;

    /**
     * Create a server failure exception
     *
     * @param errorCode {@link ErrorCode}
     */
    public ServerException(ErrorCode errorCode) {
        if (errorCode != null) {
            this.error = errorCode;
        } else {
            this.error = ErrorCode.Codes.UNKNOWN;
        }
    }

    /**
     * Create a server failure exception with a cause
     *
     * @param errorCode {@link ErrorCode}
     * @param cause     {@link Throwable}
     */
    public ServerException(ErrorCode errorCode, Throwable cause) {

        super(cause);

        if (errorCode != null) {
            this.error = errorCode;
        } else {
            this.error = ErrorCode.Codes.UNKNOWN;
        }

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
