package com.postalinfo.aop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.postalinfo.exception.AppError;
import com.postalinfo.exception.AppErrorResponse;
import com.postalinfo.exception.AppException;

/**
 * Class for Handling all the Application Exceptions
 */
@RestControllerAdvice
public class AppExceptionHandler {
	@Autowired
	Environment environment;

	/**
	 * method to handle validation exceptions
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	/**
	 * method to handle Application exceptions
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { AppException.class })
	public ResponseEntity<AppErrorResponse> appExceptionHandler(AppException e) {
		String errorMessage = (e.getError() == AppError.DATA_NOT_FOUND) ? environment.getProperty("errors.dataNotFound")
				: environment.getProperty("errors.duplicateData");
		AppErrorResponse errRes = new AppErrorResponse(e.getError().getHttpStatusCode()+"", errorMessage,e.getMessage());
		return new ResponseEntity<>(errRes, e.getError().getHttpStatusCode());
	}

	/**
	 * method to handle global/unknown errors
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { Throwable.class })
	public ResponseEntity<AppErrorResponse> exceptionHandler(Exception e) {

		AppErrorResponse errRes = new AppErrorResponse("500", "Internal Server Error");
		return new ResponseEntity<>(errRes, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
