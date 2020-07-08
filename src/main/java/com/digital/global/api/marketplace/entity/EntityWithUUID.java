package com.digital.global.api.marketplace.entity;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import lombok.Data;

@MappedSuperclass
@TypeDefs({ @TypeDef(name = "json", typeClass = JsonStringType.class),
		@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class) })
@Data
public class EntityWithUUID {

	@Id
	@Type(type = "pg-uuid")
	private UUID id;

	public EntityWithUUID() {
		this.id = UUID.randomUUID();
	}
}
