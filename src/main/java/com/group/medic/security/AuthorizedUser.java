package com.group.medic.security;

import com.group.medic.user.model.User;

import java.util.Collections;
import java.util.Objects;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public AuthorizedUser(User user) {
        super(user.getLogin(), user.getPassword(), true,
                true, true, true, Collections.emptyList());
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AuthorizedUser that = (AuthorizedUser) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user);
    }
}