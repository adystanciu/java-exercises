package com.stady.FirstGradleProject.services;

import com.stady.FirstGradleProject.model.User;
import com.stady.FirstGradleProject.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  Logger logger;

    private static final Logger errorLogger = LoggerFactory.getLogger("custom-error-logger");

    public Iterable<User> getUsers(){
        logger.debug("The getUsers method from UserService was called.");
        return userRepository.findAll();
    }

    public User addUser(User user) {
        logger.debug("The addUser method from UserService was called. ");
        User u = null;
        try {
            u = userRepository.save(user);
            logger.debug("User {} was added into DB. ", user.getUsername());
        } catch (Exception e) {
            errorLogger.error("User was not added and transaction was rollback.", e);
        }
        return u;
    }

    public User updateUser(User user) {
        logger.debug("The updateUser method from UserService was called. ");
        User userUpdated = null;
        try {
            Optional<User> userById = userRepository.findById(user.getId());
            if(userById.isPresent()){
                userUpdated = userRepository.save(user);
                logger.debug("User {} was updated into DB. ", userUpdated.getUsername());
            }
        } catch (Exception e) {
            errorLogger.error("User was not added and transaction was rollback.", e);
        }
        return userUpdated;
    }

}
