package org.neo4j.boot.test.domain;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import java.util.UUID;

public class UUIDStringConverter implements AttributeConverter<UUID, String> {

    @Override
    public String toGraphProperty(UUID uuid) {
        return uuid.toString();
    }

    @Override
    public UUID toEntityAttribute(String s) {
        return UUID.fromString(s);
    }
}
