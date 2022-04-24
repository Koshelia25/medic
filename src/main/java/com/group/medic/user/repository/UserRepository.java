package com.group.medic.user.repository;

import com.group.medic.user.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAll();

    User get(int id);

    void delete(int id);

    User add(User user);

    boolean update(User user);

    User findByLogin(String login);
}
