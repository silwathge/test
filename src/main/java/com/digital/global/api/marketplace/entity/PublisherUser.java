package com.digital.global.api.marketplace.entity;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PublisherUser extends EntityWithUUID {

	private String username;

	private String password;

	private String email;

	private boolean scope;

}
