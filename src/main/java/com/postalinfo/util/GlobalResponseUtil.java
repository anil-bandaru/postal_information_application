package com.postalinfo.util;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class for handling response for the application
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResponseUtil {
	private HttpStatus status;
	private Object data;
	private String message;

}
