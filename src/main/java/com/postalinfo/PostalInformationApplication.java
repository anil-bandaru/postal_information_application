package com.postalinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Base class for postal_information project
 */
@SpringBootApplication
public class PostalInformationApplication {
	/**
     * Application execution starting method.
     * Add listeners if applicable
     * @param args
     */
	public static void main(String[] args) {
		SpringApplication.run(PostalInformationApplication.class, args);
	}

}
