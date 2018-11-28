package org.neo4j.boot.test.domain;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.util.UUID;

@NodeEntity
public class User {

    @Id
    @Property("id")
    @Convert(value = UUIDStringConverter.class)
    private UUID id = UUID.randomUUID();

    @Property("email")
    private String email;

    public User() {
        // required by jackson and OGM version < 3.1
    }

    public User(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
