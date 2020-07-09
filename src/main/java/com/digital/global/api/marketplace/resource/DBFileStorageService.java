package com.digital.global.api.marketplace.resource;

import org.springframework.stereotype.Service;

@Service
public class DBFileStorageService {
	/*
	 * 
	 * @Autowired private ApiDcoumentRepository docRepo;
	 * 
	 * public ApiDocument storeFile(MultipartFile file) { // Normalize file name
	 * String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	 * 
	 * try { // Check if the file's name contains invalid characters if
	 * (fileName.contains("..")) { throw new
	 * FileStorageException("Sorry! Filename contains invalid path sequence " +
	 * fileName); }
	 * 
	 * ApiDocument dbFile = new ApiDocument(fileName, file.getContentType(),
	 * file.getBytes());
	 * 
	 * //return dbFileRepository.save(dbFile); } catch (IOException ex) { throw new
	 * FileStorageException("Could not store file " + fileName +
	 * ". Please try again!", ex); } }
	 * 
	 * public ApiDocument getFile(String fileId) { return docRepo.findById(fileId)
	 * .orElseThrow(() -> new MyFileNotFoundException("File not found with id " +
	 * fileId)); }
	 */}
