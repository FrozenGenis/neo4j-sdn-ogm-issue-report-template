package org.neo4j.boot.test.domain;

import org.neo4j.boot.test.domain.attributeconverters.UuidConverter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.id.UuidStrategy;

import java.util.UUID;

@NodeEntity
public class User {

    private String firstName;

    private String lastName;

    @Id @GeneratedValue(strategy = UuidStrategy.class)
    @Convert(UuidConverter.class)
    private UUID id;

    public User() {
        // required by jackson and OGM version < 3.1
    }

    public User(UUID id, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UUID getId() {
        return id;
    }
}
