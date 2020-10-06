package com.noubug.rentrepo.infrastructure.persistence.entities;

import com.noubug.rentrepo.infrastructure.persistence.enumeration.EntityStatusConstant;
import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Field;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
abstract class AbstractPersistableEntity<T extends Serializable> {
    @Id
    @Field(name = "entity_identity")
    private T id;

    private LocalDateTime dateCreated;
    private LocalDateTime dateModified = LocalDateTime.now();

    private EntityStatusConstant status = EntityStatusConstant.ACTIVE;

}
