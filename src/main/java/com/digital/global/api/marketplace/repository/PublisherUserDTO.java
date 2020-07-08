package com.digital.global.api.marketplace.repository;

public class PublisherUserDTO {

	private String username;
	private String password;

	public PublisherUserDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public PublisherUserDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
