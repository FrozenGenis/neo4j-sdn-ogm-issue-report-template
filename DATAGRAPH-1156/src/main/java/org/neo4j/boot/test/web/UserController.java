package org.neo4j.boot.test.web;

import org.neo4j.boot.test.domain.Hobby;
import org.neo4j.boot.test.domain.User;
import org.neo4j.boot.test.repository.HobbyRepository;
import org.neo4j.boot.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Transactional
@RestController
@RequestMapping(path = "/test")
public class UserController {

    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;

    @Autowired
    public UserController(UserRepository userRepository, HobbyRepository hobbyRepository) {
        this.userRepository = userRepository;
        this.hobbyRepository = hobbyRepository;
    }

    @PostMapping(path = "/users", consumes = "application/json")
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping(path = "/users/{userId}/hobbies", consumes = "application/json")
    public ResponseEntity<Hobby> create(@PathVariable UUID userId, @RequestBody Hobby hobby) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) return ResponseEntity.notFound().build();
        User user = userOptional.get();

        hobby.setHobbyist(user);
        Hobby saved = hobbyRepository.save(hobby);
        return ResponseEntity.ok(saved);
    }
}
