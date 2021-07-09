package org.application.service;

import org.application.dao.UserDao;
import org.application.entity.PersonValidator;
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

    public void deleteById(final Long id) {
        repo.delete(id);
    }

    public Optional<UserData> getById(final Long id) {
        return repo.get(id);
    }
}
