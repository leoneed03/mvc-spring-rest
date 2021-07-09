package org.application.entity;

import org.application.model.UserData;
import org.springframework.stereotype.Component;

@Component
public class PersonValidator {
    public boolean isUserValid(Person user) {
        return user.getSurname() != null
                && user.getName() != null
                && user.getAge() != null
                && !user.getName().isEmpty()
                && !user.getSurname().isEmpty()
                && user.getAge() >= 0;
    }

    public boolean isUserValidNoId(UserData user) {
        return user.getId() == null
                && isUserValid(user);
    }

    public boolean isUserValid(UserData user) {
        return user.getName() != null
                && user.getEmail() != null;
    }
}
