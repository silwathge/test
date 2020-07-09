package com.digital.global.api.marketplace.resource;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.digital.global.api.marketplace.FileStorageService;
import com.digital.global.api.marketplace.entity.ApiDocument;
import com.digital.global.api.marketplace.entity.PublisherUser;
import com.digital.global.api.marketplace.exception.UploadedFileFormatException;
import com.digital.global.api.marketplace.repository.ApiDcoumentRepository;
import com.digital.global.api.marketplace.repository.PublisherUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.parser.OpenAPIV3Parser;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/")
public class ApiResource {

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private PublisherUserRepository publisherRepo;

	@Autowired
	private ApiDcoumentRepository apiRepo;

	@PostMapping(value = "/v1/publishers/{name}/apis", consumes = { "multipart/form-data" })

	public ResponseEntity<Object> createApi(@PathVariable String name,
			@RequestParam("file") MultipartFile file) {

		PublisherUser publisher = publisherRepo.findByUsername(name);

		if (null == publisher) {
			throw new UsernameNotFoundException("No Publisher by the given name");
		}

		ObjectMapper Obj = new ObjectMapper();

		String jsonStr = "";
		try {
			jsonStr = Obj.writeValueAsString(new String(file.getBytes()));

		} catch (RuntimeException | IOException e) {
			throw new UploadedFileFormatException("File is not a JSON file");
		}

		if (null == jsonStr || "".equals(jsonStr)) {
			throw new UploadedFileFormatException("Can not find a file");
		}

		try {
			String fileName = fileStorageService.storeFile(file);
			new OpenAPIV3Parser().read(fileName);
		} catch (Error | RuntimeException e) {
			throw new UploadedFileFormatException("File is not a valid OpenAPI document file");
		}

		ApiDocument savedDoc = apiRepo.save(new ApiDocument("developer", jsonStr, false, publisher));

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedDoc.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@GetMapping("/test")
	public String testMethod() {
		/*
		 * 
		 * PublisherUser publisher = publisherRepo.save( new PublisherUser("username",
		 * bcryptEncoder.encode("password"), "kapila@kapila.com", false));
		 * 
		 * OpenAPI openAPI = new OpenAPIV3Parser().read(
		 * "/media/kapila/data2/springworkspace/global-api-marketplace/src/main/java/com/digital/global/api/marketplace/petstoreSwaggerApiDoc.json"
		 * );
		 * 
		 * ObjectMapper Obj = new ObjectMapper();
		 * 
		 * try { String jsonStr = Obj.writeValueAsString(openAPI);
		 * 
		 * apiRepo.save(new ApiDocument("developer", jsonStr, false, publisher));
		 * 
		 * System.out.println(jsonStr); }
		 * 
		 * catch (IOException e) { e.printStackTrace(); }
		 * 
		 * return messageBundle.getMessage("this.is.a.test.message.for.api.marketplace",
		 * null, LocaleContextHolder.getLocale());
		 * 
		 */
		return "this method is commented out";
	}

}
