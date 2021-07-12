package org.application.service;

import org.application.dao.UserDao;
import org.application.model.UserData;
import org.application.response.IdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserStorageService {
    @Autowired
    private UserDao repo;

    public IdResponse saveUser(final UserData user) {
        return new IdResponse(repo.save(user));
    }

    public List<UserData> getAllUsers() {
        return repo.list();
    }

    public boolean deleteById(final Long id) {
        return repo.delete(id);
    }

    public Optional<UserData> getById(final Long id) {
        return repo.get(id);
    }

    public Optional<UserData> updateIfPresent(final Long id, final UserData user) {
        return repo.updateIfPresent(id, user);
    }
}
