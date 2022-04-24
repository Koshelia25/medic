package com.group.medic.user.repository;

import com.group.medic.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class InMemoryUserRepository implements UserRepository {

    private final User ADMIN = new User(1, "Admin", "admin", "admin", "admin@gmail.com",
                                        "+380992233");

    private final User NURSE = new User(1, "Nurse", "nurse", "nurse", "nurse@gmail.com",
            "+380992232");

    private final Map<Integer, User> USER_MAP = new HashMap<>();

    {
        USER_MAP.put(1, ADMIN);
        USER_MAP.put(2, NURSE);
    }

    @Override
    public List<User> getAll() {

        return new ArrayList<>(USER_MAP.values());
    }

    @Override
    public User get(int id) {
        return USER_MAP.get(id);
    }

    @Override
    public void delete(int id) {
        USER_MAP.remove(id);
    }

    @Override
    public User add(User user) {
        int id = USER_MAP.size();
        user.setId(++id);
        return USER_MAP.put(user.getId(), user);
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }
}
