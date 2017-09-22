package com.anigenero.sandbox.poker.common.exception;

@FunctionalInterface
public interface PokerError {

    /**
     * Gets the error associated with the REV error
     *
     * @return {@link ErrorCode}
     */
    ErrorCode getError();

}
