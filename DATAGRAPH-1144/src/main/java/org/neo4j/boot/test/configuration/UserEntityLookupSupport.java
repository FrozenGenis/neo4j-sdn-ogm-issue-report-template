package org.neo4j.boot.test.configuration;

import org.neo4j.boot.test.domain.User;
import org.neo4j.boot.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.support.EntityLookupSupport;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserEntityLookupSupport extends EntityLookupSupport<User> {

    private final UserRepository repository;

    @Autowired
    public UserEntityLookupSupport(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object getResourceIdentifier(User entity) {
        if (entity == null) return null;
        return entity.getId();
    }

    @Override
    public Optional<User> lookupEntity(Object id) {
        if (!(id instanceof String)) return Optional.empty();

        UUID uuid = UUID.fromString((String) id);
        return repository.findById(uuid);
    }
}
