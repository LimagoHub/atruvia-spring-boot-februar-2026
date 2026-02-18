package de.fi.webapp.presentation.errorhandler;

public class IdMismatchException extends RuntimeException{
    public IdMismatchException() {
    }

    public IdMismatchException(final String message) {
        super(message);
    }

    public IdMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IdMismatchException(final Throwable cause) {
        super(cause);
    }

    public IdMismatchException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
