package ru.rerumu.rseeker.dao.exception;

import ru.rerumu.rseeker.crosscut.exception.ServerException;

public class FailedToFetchException extends ServerException {

    public FailedToFetchException() {
    }

    public FailedToFetchException(String message) {
        super(message);
    }

    public FailedToFetchException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToFetchException(Throwable cause) {
        super(cause);
    }

    public FailedToFetchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
