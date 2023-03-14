package rest.app.springrest.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rest.app.springrest.DaoServices.UserDaoService;
import rest.app.springrest.models.User;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDaoService userDaoService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userDaoService.findAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<User> getUserById(@PathVariable long id) {
        User user = userDaoService.findById(id);

        EntityModel<User> entityModel = EntityModel.of(user);
        // WebMvcLinkBuilder need static import !
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User newUser = userDaoService.save(user);
        //this sets location header to -> users/add-user/4  (the newUser id is 4 because we have 1,2 and 3 hardcoded)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        userDaoService.deleteUser(id);
    }
}
