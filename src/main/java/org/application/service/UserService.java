package org.application.service;

import org.application.entity.Person;
import org.application.entity.PersonEntry;
import org.application.entity.PersonValidator;
import org.application.exceptions.EmptyIdException;
import org.application.exceptions.EmptyUserException;
import org.application.exceptions.InvalidUserParametersException;
import org.application.exceptions.UserNotFoundException;
import org.application.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final PersonRepository userRepository;
    private final UserServiceMessageHelper userServiceMessageHelper;
    private final PersonValidator personValidator;

    @Autowired
    public UserService(PersonRepository userRepository,
                       UserServiceMessageHelper userServiceMessageHelper,
                       PersonValidator personValidator) {
        this.userRepository = userRepository;
        this.userServiceMessageHelper = userServiceMessageHelper;
        this.personValidator = personValidator;
    }
    
    public List<PersonEntry> getAll() {
        return userRepository.getAllUsers();
    }

    public PersonEntry saveUser(final Person user) throws EmptyUserException, InvalidUserParametersException {

        if (user == null) {
            throw new EmptyUserException(userServiceMessageHelper.getNullUserMessage());
        }

        if (!personValidator.isUserValid(user)) {
            throw new InvalidUserParametersException(userServiceMessageHelper.getInvalidUserParameters());
        }

        return userRepository.saveUser(user);
    }

    public PersonEntry getUser(Long userId) throws UserNotFoundException, EmptyIdException {

        if (userId == null) {
            throw new EmptyIdException(userServiceMessageHelper.getNullIdMessage());
        }

        Optional<PersonEntry> optionalPerson = userRepository.findByUserId(userId);

        PersonEntry personById = optionalPerson.orElseThrow(
                () -> new UserNotFoundException(userServiceMessageHelper.getUserNotFound(userId))
        );

        return personById;
    }

    public PersonEntry deleteUser(Long userId) throws UserNotFoundException, EmptyIdException {

        if (userId == null) {
            throw new EmptyIdException(userServiceMessageHelper.getNullIdMessage());
        }

        Optional<PersonEntry> optionalPerson = userRepository.deleteUserById(userId);
        PersonEntry personDeleted = optionalPerson.orElseThrow(
                () -> new UserNotFoundException(userServiceMessageHelper.getUserNotFound(userId))
        );

        return personDeleted;
    }

    public PersonEntry updateUser(final Long userId,
                                  final Person user) throws UserNotFoundException, EmptyUserException, EmptyIdException, InvalidUserParametersException {

        if (user == null) {
            throw new EmptyUserException(userServiceMessageHelper.getNullUserMessage());
        }

        if (userId == null) {
            throw new EmptyIdException(userServiceMessageHelper.getNullIdMessage());
        }

        if (!personValidator.isUserValid(user)) {
            throw new InvalidUserParametersException(userServiceMessageHelper.getInvalidUserParameters());
        }

        Optional<PersonEntry> updatedPersonEntry = userRepository.updateUserById(userId, user);

        PersonEntry personUpdated = updatedPersonEntry.orElseThrow(
                () -> new UserNotFoundException(userServiceMessageHelper.getUserNotFound(userId))
        );

        return personUpdated;
    }
}
