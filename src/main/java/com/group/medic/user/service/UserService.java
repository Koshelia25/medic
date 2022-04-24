package com.group.medic.user.service;

import com.group.medic.user.model.User;
import com.group.medic.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return repository.getAll();
    }

    public User get(int id) {
        return repository.get(id);
    }

    //TODO when delete user delete all relations to docs
    public void delete(int id) {
        repository.delete(id);
    }

    public User add(User user) {
        return repository.add(user);
    }

    public boolean update(User user) {
        return repository.update(user);
    };

    public User findByLogin(String login) {
        return repository.findByLogin(login);
    }

}
