package me.manong.user.service;

import me.manong.user.entity.User;

import java.util.List;

public interface UserService {
    public User getUserById(Long id);

    public List<User> getAllUser();

    public User getUserByUsername(String username);
}
