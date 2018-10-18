package org.neo4j.boot.test.domain.attributeconverters;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import java.util.Objects;
import java.util.UUID;

public class UuidConverter implements AttributeConverter<UUID, String> {

    @Override
    public String toGraphProperty(UUID value) {
        Objects.requireNonNull(value, "Entity attribute id cannot be null");
        return value.toString();
    }

    @Override
    public UUID toEntityAttribute(String value) {
        Objects.requireNonNull(value, "Graph property id cannot be null");
        return UUID.fromString(value);
    }
}
