package com.digital.global.api.marketplace.resource;

import java.net.URI;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.digital.global.api.marketplace.entity.ApiDocument;
import com.digital.global.api.marketplace.entity.PublisherUser;
import com.digital.global.api.marketplace.exception.PublisherNotFoundException;
import com.digital.global.api.marketplace.exception.UploadedFileFormatException;
import com.digital.global.api.marketplace.service.ApiDocumentService;
import com.digital.global.api.marketplace.service.FileHandlingService;
import com.digital.global.api.marketplace.service.PublisherService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/")

public class ApiResource {

	@Autowired
	private FileHandlingService fileHandlingService;

	@Autowired
	private PublisherService publisherService;

	@Autowired
	private ApiDocumentService apiDocumentService;

	@ApiOperation(value = "create OpenAPI Document for the given publisher in {name}."
			+ "File should be valid OpenApi Json. User must be authorized")

	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Submitted document created.Location header is set with new URI"),
			@ApiResponse(code = 400, message = "Incorrect versiion of document supplied"),
			@ApiResponse(code = 401, message = "User is unauthorized"),
			@ApiResponse(code = 415, message = "Incorrect file type supplied"),
			@ApiResponse(code = 403, message = "User is forbidden to this resource"),
			@ApiResponse(code = 404, message = "Publisher not found") })

	@PostMapping(value = "/v1/publishers/{name}/apis", consumes = { "multipart/form-data" })
	public ResponseEntity<Object> createApi(@PathVariable @NotBlank String name,
			@RequestParam("file") MultipartFile file)
			throws PublisherNotFoundException, UploadedFileFormatException {

		PublisherUser publisher = publisherService.findPublisherByname(name);

		String fileAsString = fileHandlingService.getFileAsString(file);

		ApiDocument savedDoc = apiDocumentService
				.createApiDocument(new ApiDocument("developer", fileAsString, false, publisher));

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedDoc.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

}
