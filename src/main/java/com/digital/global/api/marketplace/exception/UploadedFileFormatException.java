package com.digital.global.api.marketplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UploadedFileFormatException extends RuntimeException {
	public UploadedFileFormatException(String message) {
		super(message);
	}

	public UploadedFileFormatException(String message, Throwable cause) {
		super(message, cause);
	}
}
