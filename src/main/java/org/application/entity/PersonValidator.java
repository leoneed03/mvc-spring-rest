package org.application.entity;

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
}
