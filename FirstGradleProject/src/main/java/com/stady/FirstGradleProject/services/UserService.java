package com.stady.FirstGradleProject.services;

import com.stady.FirstGradleProject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> getUsers();

    public User getUsersById(Long id);

    public User addUser(User user) throws Exception;

    public User updateUser(User user) throws Exception;

    public boolean deleteUser(User user) throws Exception;

    public Optional<User> getUserByUsername(String username);

}
