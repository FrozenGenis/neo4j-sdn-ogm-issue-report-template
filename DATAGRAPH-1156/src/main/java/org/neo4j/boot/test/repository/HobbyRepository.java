package org.neo4j.boot.test.repository;

import org.neo4j.boot.test.domain.Hobby;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface HobbyRepository extends Neo4jRepository<Hobby, UUID> {

}
