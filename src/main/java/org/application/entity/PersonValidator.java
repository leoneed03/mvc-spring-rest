package org.application.entity;

import org.application.model.UserData;
import org.springframework.stereotype.Component;

@Component
public class PersonValidator {

    public boolean isUserValidNoId(UserData user) {
        return user.getId() == null
                && isUserValid(user);
    }

    public boolean isUserValid(UserData user) {
        return user.getName() != null
                && user.getEmail() != null;
    }
}
