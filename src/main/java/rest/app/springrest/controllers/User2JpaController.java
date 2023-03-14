package rest.app.springrest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rest.app.springrest.models.User;
import rest.app.springrest.models.User2JPA;
import rest.app.springrest.repositories.UserRepository2JPA;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class User2JpaController {

    /*It`s wrong to have Repository here. Controller should talk to Service and Service should talk to Repository
     * This is just for Example purpose*/
    private final UserRepository2JPA repository;

    @PostMapping("/add-user")
    @ResponseStatus(HttpStatus.OK)
    public void addUser(@RequestBody User2JPA user) {
        repository.save(user);
    }

    @GetMapping("/get-user/{id}")
    public Optional<User2JPA> getUserById(@PathVariable long id) {
        return repository.findById(id);
    }
}
