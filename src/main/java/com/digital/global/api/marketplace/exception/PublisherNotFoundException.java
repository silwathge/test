package com.digital.global.api.marketplace.exception;

public class PublisherNotFoundException extends RuntimeException {

	public PublisherNotFoundException(String message) {

		super(message);

	}

	public PublisherNotFoundException(String message, Throwable cause) {

		super(message, cause);
	}

}
