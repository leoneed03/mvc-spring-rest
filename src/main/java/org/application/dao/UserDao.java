package org.application.dao;

import org.application.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    List<User> list();
}
