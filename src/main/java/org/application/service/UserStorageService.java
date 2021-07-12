package org.application.service;

import org.application.repository.UserDataRepo;
import org.application.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@org.springframework.transaction.annotation.Transactional
@Service
public class UserStorageService {
    @Autowired
    private UserDataRepo repo;

    @Transactional
    @org.springframework.transaction.annotation.Transactional
    public UserData saveUser(final UserData user) {
        UserData userSaved = repo.save(user);
        repo.flush();
        return userSaved;
    }

    public List<UserData> getAllUsers() {
        return repo.findAll();
    }

    @org.springframework.transaction.annotation.Transactional
    @Transactional
    public boolean deleteById(final Long id) {
        System.out.println("Service delete by id = " + id);

        UserData userData = new UserData();
        userData.setId(id);
        repo.delete(userData);
        return true;
//        return repo.deleteUserDataById(id) != null;
    }

    public Optional<UserData> getById(final Long id) {
        return repo.findById(id);
    }

    public Optional<UserData> updateIfPresent(final Long id,
                                              final UserData user) {
        Optional<UserData> userDataFoundOpt = repo.findById(id);

        if (userDataFoundOpt.isEmpty()) {
            return Optional.empty();
        }

        UserData userDataFound = userDataFoundOpt.get();

        userDataFound.setName(user.getName());
        userDataFound.setEmail(user.getEmail());

        return Optional.of(userDataFound);
    }
}
