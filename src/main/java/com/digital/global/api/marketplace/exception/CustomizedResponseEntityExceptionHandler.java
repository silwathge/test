package com.digital.global.api.marketplace.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PublisherNotFoundException.class)
	public final ResponseEntity<Object> handlePublisherNotFoundException(PublisherNotFoundException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UploadedFileFormatException.class)
	public final ResponseEntity<Object> handleFileNotProvidedException(UploadedFileFormatException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public final ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public final ResponseEntity<Object> handleMaxUploadSizeExceededException(
			MaxUploadSizeExceededException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
				"Maximum upload size exceeded", request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Unsupported media type",
				request.getDescription(false));

		return new ResponseEntity(exceptionResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Invalid URI",
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}
