package com.group.medic.security;

import com.group.medic.user.model.User;
import com.group.medic.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new AuthorizedUser(user);
    }
}