package com.local.ecom.util.exception;

import static com.local.ecom.util.EComLogger.logError;

public class APIExceptionHandler {
    public static APIException logAndCreateAPIException(String message) {
        APIException apiException = new APIException(message);
        logError("Exception occurred while executing API operation.",apiException);
        return apiException;
    }
}
