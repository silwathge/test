package com.digital.global.api.marketplace.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digital.global.api.marketplace.entity.PublisherUser;

@Repository
public interface PublisherUserRepository extends JpaRepository<PublisherUser, UUID> {

	public PublisherUser findByUsername(String username);

}
