package org.application.controller;

import org.application.model.UserDataValidator;
import org.application.exceptions.UserException;
import org.application.model.UserData;
import org.application.response.IdResponse;
import org.application.service.UserServiceMessageHelper;
import org.application.service.UserStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userdata")
public class UserDataController {
    private final UserStorageService userStorageService;
    private final UserDataValidator userDataValidator;
    private final UserServiceMessageHelper userServiceMessageHelper;

    @Autowired
    public UserDataController(UserStorageService userStorageService,
                              UserDataValidator userDataValidator,
                              UserServiceMessageHelper userServiceMessageHelper) {
        this.userStorageService = userStorageService;
        this.userDataValidator = userDataValidator;
        this.userServiceMessageHelper = userServiceMessageHelper;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse saveUser(@RequestBody UserData user) throws UserException {

        if (user == null) {

            throw new UserException(
                    userServiceMessageHelper.getNullUserMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        if (!userDataValidator.isUserValidNoId(user)) {

            throw new UserException(userServiceMessageHelper.getInvalidUserParameters(),
                    HttpStatus.BAD_REQUEST);
        }

        try {

            return userStorageService.saveUser(user);

        } catch (ValidationException constraintViolationException) {

            throw new UserException(userServiceMessageHelper.getInvalidUserParameters(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable("id") Long userId,
                           @RequestBody UserData user) throws UserException {

        if (user == null) {

            throw new UserException(
                    userServiceMessageHelper.getNullUserMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        if (!userDataValidator.isUserValidNoId(user)) {

            throw new UserException(
                    userServiceMessageHelper.getInvalidUserParameters(),
                    HttpStatus.BAD_REQUEST);
        }

        try {

            userStorageService.updateIfPresent(userId, user).orElseThrow(
                    () -> new UserException(userServiceMessageHelper.getUserNotFound(userId),
                            HttpStatus.BAD_REQUEST)
            );

        } catch (ValidationException constraintViolationException) {

            throw new UserException(userServiceMessageHelper.getInvalidUserParameters(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public List<UserData> getAllUsers() {
        return userStorageService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long userId) throws UserException {

        boolean userWasFound = userStorageService.deleteById(userId);

        if (!userWasFound) {

            throw new UserException(userServiceMessageHelper.getUserNotFound(userId),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public UserData getUser(@PathVariable("id") Long userId) throws UserException {

        Optional<UserData> userDataFound = userStorageService.getById(userId);

        return userDataFound.orElseThrow(
                () -> new UserException(userServiceMessageHelper.getUserNotFound(userId),
                        HttpStatus.BAD_REQUEST)
        );
    }
}
