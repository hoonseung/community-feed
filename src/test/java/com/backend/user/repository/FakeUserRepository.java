package com.backend.user.repository;

import com.backend.user.application.interfaces.UserRepository;
import com.backend.user.domain.User;
import java.util.HashMap;
import java.util.Map;

public class FakeUserRepository implements UserRepository {

    private final Map<Long, User> store = new HashMap<>();
    private static Long id = 1L;


    @Override
    public User save(User user) {
        if (user.getId() != null) {
            store.put(user.getId(), user);
            return user;
        }
        Long userId = id++;
        User newUser = new User(userId, user.getUserInfo());
        store.put(userId, newUser);
        return newUser;
    }

    @Override
    public User findById(Long id) {
        return store.get(id);
    }
}
