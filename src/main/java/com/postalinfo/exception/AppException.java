package com.postalinfo.exception;

/**
 * App exception handler class
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = -5323704932512842804L;

    private final AppError error;
    private final String   errorData;

    /**
     * Constructor an AppExcpetion from given error, errorData, cause
     * @param error used for error code
     * @param errorData used for errorData 
     * @param cause error cause
     */
    public AppException(AppError error, String errorData, Throwable cause) {
        super(cause);
        this.error = error;
        this.errorData = errorData;
    }

    /**
     * Constructor an AppExcpetion from given error, errorData
     * @param error used for error code
     * @param errorData used for errorData 
     */
    public AppException(AppError error, String errorData) {
        this(error, errorData, null);
    }

    /**
     * Constructor an AppExcpetion from given error, cause
     * @param error used for error code
     * @param cause error cause
     */
    public AppException(AppError error, Throwable cause) {
        this(error, null, cause);
    }

    /**
     * Constructor an AppExcpetion from a String
     * @param error used for error code
     */
    public AppException(AppError error) {
        this(error, null, null);
    }

    /**
     * Constructor an AppExcpetion from a Throwable
     * @param cause error cause
     */
    public AppException(Throwable cause) {
        this(AppError.UNKNOWN_ERROR, null, cause);
    }

    /**
     * @return
     */
    public AppError getError() {
        return error;
    }

    /**
     *
     */
    @Override
    public String toString() { return getMessage(); }

    /**
     *
     */
    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder("AppError:").append(error.name());
        if (errorData != null) {
            sb.append("\nData: ").append(errorData);
        }
        if (getCause() != null) {
            sb.append("\nCause: ").append(getCause().getMessage());
        }
        return sb.toString();
    }
}
