package org.application.repository;

import org.application.entity.Person;
import org.application.entity.PersonEntry;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Repo implements PersonRepository {
    private final Map<Long, Person> users = new LinkedHashMap<>();

    @Override
    public Optional<PersonEntry> findByUserId(Long userId) {

        if (users.containsKey(userId)) {

            return Optional.of(new PersonEntry(userId, users.get(userId)));
        } else {

            return Optional.empty();
        }
    }

    @Override
    public PersonEntry saveUser(final Person person) {
        long idToPut = users.size();
        users.put(idToPut, person);

        return new PersonEntry(idToPut, person);
    }

    @Override
    public List<PersonEntry> getAllUsers() {

        return users.entrySet().stream()
                .map(x -> new PersonEntry(x.getKey(), x.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PersonEntry> deleteUserById(final Long userId) {

        if (users.containsKey(userId)) {
            Person personFound = users.remove(userId);

            return Optional.of(new PersonEntry(userId, personFound));
        } else {

            return Optional.empty();
        }
    }

    @Override
    public Optional<PersonEntry> updateUserById(Long userId, final Person user) {
        if (users.containsKey(userId)) {
            Person personUpdated = users.put(userId, user);

            return Optional.of(new PersonEntry(userId, personUpdated));
        } else {

            return Optional.empty();
        }
    }
}
