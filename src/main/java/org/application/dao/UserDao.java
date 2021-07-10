package org.application.dao;

import org.application.exceptions.UserException;
import org.application.model.UserData;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Long save(UserData user);

    List<UserData> list();

    void delete(Long id);

    Optional<UserData> get(Long id);

    Optional<UserData> updateIfPresent(Long id, UserData user);
}
