package org.application.service;

import org.application.dao.UserDao;
import org.application.entity.PersonValidator;
import org.application.exceptions.EmptyUserException;
import org.application.exceptions.InvalidUserParametersException;
import org.application.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStorageService {
    @Autowired
    private UserDao repo;
    @Autowired
    private UserServiceMessageHelper userServiceMessageHelper;
    @Autowired
    private PersonValidator personValidator;

    public void saveUser(final UserData user) throws EmptyUserException, InvalidUserParametersException {

        if (user == null) {
            throw new EmptyUserException(userServiceMessageHelper.getNullUserMessage());
        }

        if (!personValidator.isUserValid(user)) {
            throw new InvalidUserParametersException(userServiceMessageHelper.getInvalidUserParameters());
        }

        repo.save(user);
    }
}
