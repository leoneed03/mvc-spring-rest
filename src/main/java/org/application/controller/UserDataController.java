package org.application.controller;

import org.application.entity.PersonValidator;
import org.application.exceptions.UserException;
import org.application.model.UserData;
import org.application.response.IdResponse;
import org.application.service.UserServiceMessageHelper;
import org.application.service.UserStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userdata")
public class UserDataController {
    @Autowired
    UserStorageService userStorageService;
    @Autowired
    PersonValidator personValidator;
    @Autowired
    UserServiceMessageHelper userServiceMessageHelper;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse saveUser(@RequestBody UserData user) throws UserException {

        if (user == null) {
            throw new UserException(
                    userServiceMessageHelper.getNullUserMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        if (!personValidator.isUserValidNoId(user)) {
            throw new UserException(userServiceMessageHelper.getInvalidUserParameters(),
                    HttpStatus.BAD_REQUEST);
        }

        return userStorageService.saveUser(user);
    }

    @PutMapping("/{id}")
    public UserData updateUser(@PathVariable("id") Long userId,
                               @RequestBody UserData user) throws UserException {

        if (user == null) {
            throw new UserException(
                    userServiceMessageHelper.getNullUserMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        if (!personValidator.isUserValidNoId(user)) {
            throw new UserException(
                    userServiceMessageHelper.getInvalidUserParameters(),
                    HttpStatus.BAD_REQUEST);
        }

        return userStorageService.updateIfPresent(userId, user).orElseThrow(
                () -> new UserException(userServiceMessageHelper.getUserNotFound(userId),
                        HttpStatus.BAD_REQUEST)
        );
    }

    @GetMapping("/")
    public List<UserData> getAllUsers() {
        return userStorageService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long userId) {
        userStorageService.deleteById(userId);
    }

    @GetMapping("/{id}")
    public UserData getUser(@PathVariable("id") Long userId) throws UserException {

        System.out.println("trying to find by " + userId);
        Optional<UserData> userDataFound = userStorageService.getById(userId);

        if (userDataFound.isPresent()) {
            System.out.println(userDataFound.get());
        } else {
            System.out.println("EMPTY OPTIONAL");
        }

        return userDataFound.orElseThrow(
                () -> new UserException(userServiceMessageHelper.getUserNotFound(userId),
                        HttpStatus.BAD_REQUEST)
        );
    }
}
