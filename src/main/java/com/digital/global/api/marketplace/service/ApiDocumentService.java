package com.digital.global.api.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.global.api.marketplace.entity.ApiDocument;
import com.digital.global.api.marketplace.repository.ApiDcoumentRepository;

@Service
public class ApiDocumentService {

	@Autowired
	private ApiDcoumentRepository apiRepo;

	public ApiDocument createApiDocument(ApiDocument doc) {

		return apiRepo.save(doc);

	}

}
