package org.neo4j.boot.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.neo4j.boot.test.domain.Hobby;
import org.neo4j.boot.test.domain.User;
import org.neo4j.boot.test.repository.HobbyRepository;
import org.neo4j.boot.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@DataNeo4jTest
@RunWith(SpringRunner.class)
public class RepositoryIntegrationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HobbyRepository hobbyRepository;

    @Before
    public void setup() {
        User user = new User("test@neo4j.com");
        userRepository.save(user);
    }

    @Test
    public void testRelationship() {
        Optional<User> userOptional = userRepository.findByEmail("test@neo4j.com");
        assert(userOptional).isPresent();
        User user = userOptional.get();

        Hobby hobby = new Hobby("Tennis");
        hobby.setHobbyist(user);
        Hobby result = hobbyRepository.save(hobby);
        assert(result != null);
    }
}
