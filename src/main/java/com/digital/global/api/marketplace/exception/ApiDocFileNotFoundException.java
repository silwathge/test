package com.digital.global.api.marketplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiDocFileNotFoundException extends RuntimeException {

	public ApiDocFileNotFoundException(String message) {
		super(message);
	}

	public ApiDocFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
