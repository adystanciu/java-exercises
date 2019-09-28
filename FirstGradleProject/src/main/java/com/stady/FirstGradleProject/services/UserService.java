package com.stady.FirstGradleProject.services;

import com.stady.FirstGradleProject.errors.UserNotFoundException;
import com.stady.FirstGradleProject.model.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers();

    public User getUsersById(Long id);

    public User addUser(User user) throws Exception;

    public User updateUser(User user) throws UserNotFoundException, Exception;

    public boolean deleteUser(User user) throws UserNotFoundException, Exception;

    public User getUserByUsername(String username) throws UserNotFoundException;

}
