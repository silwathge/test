package com.digital.global.api.marketplace.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.digital.global.api.marketplace.exception.UploadedFileFormatException;

import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileHandlingService {

	public String getFileAsString(MultipartFile file) {

		String sentStr;
		try {
			sentStr = new String(file.getBytes());
			ParseOptions options = new ParseOptions();
			options.setResolve(true);
			options.setResolveFully(true);
			SwaggerParseResult parsedContents = new OpenAPIV3Parser().readContents(sentStr, null, options);

			if (null == parsedContents.getOpenAPI()) {

				throw new UploadedFileFormatException("Not a valid OpenAPI document");
			}

		} catch (IOException e) {
			log.error("" + e);
			throw new UploadedFileFormatException("Not a valid OpenAPI document");

		}

		return sentStr;
	}

}