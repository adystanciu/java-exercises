package com.stady.FirstGradleProject.services;

import com.stady.FirstGradleProject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getUsers();

    public User addUser(User user);

    public User updateUser(User user);

    public boolean deleteUser(User user);

    public Optional<User> getUserByUsername(String username);

}
