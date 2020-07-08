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

		PublisherUser publisher = publisherRepo.save(new PublisherUser("username1",
				bcryptEncoder.encode("password1"), "kapila@kapila.com", false));

		/*
		 * Swagger swagger = new
		 * SwaggerParser().read("http://petstore.swagger.io/v2/swagger.json");
		 *//*
			 * System.out.println(openAPI.getPaths());
			 * System.out.println(openAPI.getComponents());
			 * System.out.println(openAPI.getExtensions());
			 * System.out.println(openAPI.getExternalDocs());
			 * System.out.println(openAPI.getInfo());
			 * System.out.println(openAPI.getPaths());
			 */

		/* apiRepo.save(new ApiDocument("developer", null, false, publisher)); */

	}
}
