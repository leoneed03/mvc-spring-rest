package org.application.controller;

import org.application.dao.UserDao;
import org.application.entity.Person;
import org.application.entity.PersonEntry;
import org.application.exceptions.EmptyIdException;
import org.application.exceptions.EmptyUserException;
import org.application.exceptions.InvalidUserParametersException;
import org.application.exceptions.UserNotFoundException;
import org.application.model.UserData;
import org.application.response.MessageResponse;
import org.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    UserDao repo;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<?> saveUser(@RequestBody Person user) {

        try {
            PersonEntry savedUser = userService.saveUser(user);

            //TODO: return created status
            return ResponseEntity.ok().body(savedUser);

        } catch (EmptyUserException | InvalidUserParametersException userException) {

            return ResponseEntity.badRequest().body(new MessageResponse(userException.getMessage()));
        }

    }

    @PostMapping("/data")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserData user) {

        repo.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long userId) {

        try {
            PersonEntry deletedUser = userService.deleteUser(userId);

            return ResponseEntity.ok().body(deletedUser);

        } catch (UserNotFoundException | EmptyIdException invalidUserException) {

            return ResponseEntity.badRequest().body(new MessageResponse(invalidUserException.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long userId) {

        try {
            PersonEntry PersonFound = userService.getUser(userId);

            return ResponseEntity.ok().body(PersonFound);

        } catch (UserNotFoundException | EmptyIdException invalidUserException) {

            return ResponseEntity.badRequest().body(new MessageResponse(invalidUserException.getMessage()));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable("id") Long userId,
                                            @RequestBody Person user) {

        try {
            PersonEntry userUpdated = userService.updateUser(userId, user);

            return ResponseEntity.ok().body(userUpdated);

        } catch (UserNotFoundException | EmptyUserException | EmptyIdException | InvalidUserParametersException userException) {
            return ResponseEntity.badRequest().body(new MessageResponse(userException.getMessage()));
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {

        List<PersonEntry> allUsers = userService.getAll();

        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllUsersHibernate() {

//        UserData user = new UserData();
//        user.setName("LEO1");
//        user.setEmail("leo1@mail.ru");
//
//        repo.save(user);
        System.out.println("all user to show");
        List<UserData> allUsers = repo.list();

        System.out.println(allUsers);

        return ResponseEntity.ok(allUsers);
    }
}
