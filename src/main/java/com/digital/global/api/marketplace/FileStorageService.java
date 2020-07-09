package com.digital.global.api.marketplace;

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

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath()
				.normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new UploadedFileFormatException(
					"Could not create the directory where the uploaded files will be stored.", ex);
		}
	}

	public String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new UploadedFileFormatException("Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			long copy = Files.copy(file.getInputStream(), targetLocation,
					StandardCopyOption.REPLACE_EXISTING);

			return targetLocation.toString();
		} catch (IOException ex) {
			throw new UploadedFileFormatException("File is not provided", ex);
		}
	}

//	public Resource loadFileAsResource(String fileName) {
//		try {
//			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
//			Resource resource = new UrlResource(filePath.toUri());
//			if (resource.exists()) {
//				return resource;
//			} else {
//				throw new MyFileNotFoundException("File not found " + fileName);
//			}
//		} catch (MalformedURLException ex) {
//			throw new MyFileNotFoundException("File not found " + fileName, ex);
//		}
//	}
}