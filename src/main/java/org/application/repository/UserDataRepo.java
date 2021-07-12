package org.application.repository;

import org.application.model.UserData;

import java.util.List;
import java.util.Optional;

public interface UserDataRepo {
    Long save(UserData user);

    List<UserData> list();

    boolean delete(Long id);

    Optional<UserData> get(Long id);

    Optional<UserData> updateIfPresent(Long id, UserData user);
}
