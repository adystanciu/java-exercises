package com.stady.FirstGradleProject.services;

import com.stady.FirstGradleProject.model.User;
import com.stady.FirstGradleProject.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Logger logger;

    private static final Logger errorLogger = LoggerFactory.getLogger("custom-error-logger");

    @Override
    public List<User> getUsers() {
        logger.debug("The getUsers method from UserServiceImpl was called.");
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        logger.debug("The addUser method from UserServiceImpl was called. ");
        User u = null;
        try {
            u = userRepository.save(user);
            logger.debug("User {} was added into DB. ", user.getUsername());
        } catch (Exception e) {
            errorLogger.error("User was not added and transaction was rollback.", e);
        }
        return u;
    }

    @Override
    public User updateUser(User user) {
        logger.debug("The updateUser method from UserServiceImpl was called. ");
        User userUpdated = null;
        try {
            Optional<User> userById = userRepository.findById(user.getId());
            if (userById.isPresent()) {
                userUpdated = userRepository.save(user);
                logger.debug("User {} was updated into DB. ", userUpdated.getUsername());
            }
        } catch (Exception e) {
            errorLogger.error("User was not updated and transaction was rollback.", e);
        }
        return userUpdated;
    }

    @Override
    public boolean deleteUser(User user) {
        logger.debug("The deleteUser method from UserServiceImpl was called. ");
        try {
            Optional<User> userById = userRepository.findById(user.getId());
            if (userById.isPresent()) {
                userRepository.delete(user);
                logger.debug("User with id: {} was deleted from DB. ", user.getId());
                return true;
            }
        } catch (Exception e) {
            errorLogger.error("User was not deleted and transaction was rollback.", e);
        }
        return false;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        logger.debug("The getUserByUsername method from UserServiceImpl was called. ");

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            logger.debug("User {} was deleted from DB. ", user.get().getUsername());
            return user;
        }
            logger.debug("User with username: {} is not present into DB. ", user.get().getUsername());
            return Optional.empty();
    }

}
