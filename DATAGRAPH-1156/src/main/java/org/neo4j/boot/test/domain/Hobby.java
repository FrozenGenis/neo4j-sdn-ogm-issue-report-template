package org.neo4j.boot.test.domain;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.util.UUID;

@NodeEntity
public class Hobby {

    @Id
    @Property("id")
    @Convert(value = UUIDStringConverter.class)
    private UUID id = UUID.randomUUID();

    @Property("name")
    private String name;

    @Relationship(value = "HAS", direction = Relationship.INCOMING)
    private User hobbyist;

    public Hobby() {
    }

    public Hobby(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getHobbyist() {
        return hobbyist;
    }

    public void setHobbyist(User hobbyist) {
        this.hobbyist = hobbyist;
    }
}
