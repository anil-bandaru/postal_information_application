package com.postalinfo.exception;

import java.io.Serializable;

public class AppErrorResponse implements Serializable {

    private static final long serialVersionUID = 8147087417957089168L;

    private String code;
    private String message;
    private String errorData;

    /**
     * default Constructor
     */
    public AppErrorResponse() {}
    
    public AppErrorResponse(String code) {
        this.code = code;
    }
    
    public AppErrorResponse(String code, String message) {
        this(code);
        this.message = message;
    }
    public AppErrorResponse(String code, String message,String errorData) {
        this(code);
        this.message = message;
        this.errorData=errorData;
    }

    public String getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    public String errorData() {
    	return errorData;
    }
}
