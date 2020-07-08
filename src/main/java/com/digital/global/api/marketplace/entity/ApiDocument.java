package com.digital.global.api.marketplace.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiDocument extends EntityWithUUID {

	private String tag;

	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	@Basic(fetch = FetchType.LAZY)
	private String content;

	private boolean isScopePrivate;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_apidoc_publisher"))
	private PublisherUser publisher;

}
