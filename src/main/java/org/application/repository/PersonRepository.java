package org.application.repository;

import org.application.entity.Person;
import org.application.entity.PersonEntry;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {
    Optional<PersonEntry> findByUserId(Long userId);

    PersonEntry saveUser(Person person);

    List<PersonEntry> getAllUsers();

    Optional<PersonEntry> deleteUserById(Long userId);

    PersonEntry updateUserById(Long userId, Person user);
}
