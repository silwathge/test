package com.digital.global.api.marketplace.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digital.global.api.marketplace.entity.ApiDocument;

@Repository
public interface ApiDcoumentRepository extends JpaRepository<ApiDocument, UUID> {

}
