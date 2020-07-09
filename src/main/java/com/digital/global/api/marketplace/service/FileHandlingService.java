package com.digital.global.api.marketplace.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.digital.global.api.marketplace.config.FileStorageProperties;
import com.digital.global.api.marketplace.exception.UploadedFileFormatException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.parser.OpenAPIV3Parser;

@Service
public class FileHandlingService {

	@Autowired
	private FileHandlingService fileHandlingService;

	private final Path fileStorageLocation;

	@Autowired
	public FileHandlingService(FileStorageProperties fileStorageProperties) {

		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath()
				.normalize();

		try {

			Files.createDirectories(this.fileStorageLocation);

		} catch (Exception ex) {
			throw new UploadedFileFormatException(
					"Could not create the directory where the uploaded files will be stored.", ex);
		}
	}

	public String getFileAsString(MultipartFile file) {

		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = "";

		try {
			jsonStr = Obj.writeValueAsString(new String(file.getBytes()));

		} catch (RuntimeException | IOException e) {
			throw new UploadedFileFormatException("File is not a valid OpenAPI document file");
		}

		if (null == jsonStr || "".equals(jsonStr)) {
			throw new UploadedFileFormatException("File is not a valid OpenAPI document file");
		}

		String fileName = "";
		try {

			fileName = fileHandlingService.storeFile(file);
			new OpenAPIV3Parser().read(fileName);

		} catch (Error | RuntimeException e) {
			throw new UploadedFileFormatException("File is not a valid OpenAPI document file");
		} finally {
			File tempFile = new File(fileName);
			tempFile.delete();
		}

		return jsonStr;
	}

	public String storeFile(MultipartFile file) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {

			if (fileName.contains("..")) {
				throw new UploadedFileFormatException("Filename contains invalid path sequence " + fileName);
			}

			Path targetLocation = this.fileStorageLocation.resolve(fileName);

			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return targetLocation.toString();

		} catch (IOException ex) {
			throw new UploadedFileFormatException("File is not provided", ex);
		}
	}

}