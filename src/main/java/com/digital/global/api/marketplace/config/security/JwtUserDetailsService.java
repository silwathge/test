package com.digital.global.api.marketplace.config.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digital.global.api.marketplace.entity.PublisherUser;
import com.digital.global.api.marketplace.repository.PublisherUserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private PublisherUserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		PublisherUser findByUsername = repo.findByUsername(username);

		if (findByUsername == null) {

			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		if (findByUsername.getUsername().equals(username)) {
			return new User(findByUsername.getUsername(), findByUsername.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
