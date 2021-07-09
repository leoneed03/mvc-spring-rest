package org.application.dao;

import org.application.model.UserData;

import java.util.List;

public interface UserDao {
    void save(UserData user);
    List<UserData> list();
}
