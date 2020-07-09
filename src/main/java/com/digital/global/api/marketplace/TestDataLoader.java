package com.digital.global.api.marketplace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.digital.global.api.marketplace.entity.PublisherUser;
import com.digital.global.api.marketplace.repository.ApiDcoumentRepository;
import com.digital.global.api.marketplace.repository.PublisherUserRepository;

@Component
public class TestDataLoader implements ApplicationRunner {

	@Autowired
	private PublisherUserRepository publisherRepo;

	@Autowired
	private ApiDcoumentRepository apiRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public void run(ApplicationArguments args) {

		PublisherUser testUserInDB = publisherRepo.findByUsername("username");
		if (null == testUserInDB) {
			PublisherUser publisher = publisherRepo.save(new PublisherUser("username",
					bcryptEncoder.encode("password"), "kapila@kapila.com", false));
		}

	}
}
