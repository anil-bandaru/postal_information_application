package com.postalinfo.exception;

import org.springframework.http.HttpStatus;

/**
 * App Error that can be used
 * all error codes are custom codes
 * these errors will be used in the following
 *  a) unknown
 *  b) user 
 */
public enum AppError {

    EMPTY_DATA_INPUT(HttpStatus.BAD_REQUEST),
    CREATE_FAILED,
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND),
    DATA_EXISTS,
    UNKNOWN_ERROR;

    // variable for using status code
    // initialization
    private HttpStatus httpStatusCode;

    // default Constructor
    //by default,
    //    status code is Internal Server Error
    private AppError() {
        this(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Constructor with httpStatusCode as parameter
    // preparing AppError object
    private AppError(HttpStatus httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * to get Http status code
     * return int
     * @return
     */
    public int getHttpStatusCodeValue() { return httpStatusCode.value(); }
    public HttpStatus getHttpStatusCode() { return httpStatusCode; }
}
