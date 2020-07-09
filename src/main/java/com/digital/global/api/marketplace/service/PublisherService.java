package com.digital.global.api.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digital.global.api.marketplace.entity.PublisherUser;
import com.digital.global.api.marketplace.repository.PublisherUserRepository;

@Service
public class PublisherService {

	@Autowired
	private PublisherUserRepository publisherRepo;

	public PublisherUser findPublisherByname(String username) {

		PublisherUser publisher = publisherRepo.findByUsername(username);

		if (null == publisher) {

			throw new UsernameNotFoundException("No Publisher by the given name");

		}

		return publisher;
	}
}
