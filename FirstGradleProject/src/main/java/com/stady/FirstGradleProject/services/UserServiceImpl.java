package com.stady.FirstGradleProject.services;

import com.stady.FirstGradleProject.errors.UserNotFoundException;
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

    private static final Logger errorLogger = LoggerFactory.getLogger("custom-errors-logger");

    @Override
    public List<User> getUsers() {
        logger.debug("The getUsers method from UserServiceImpl was called.");
        return userRepository.findAll();
    }

    @Override
    public User getUsersById(Long id) {
        logger.debug("The getUserById method from UserServiceImpl was called.");
        final Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) throw new UserNotFoundException("User with id: "+ id + " does not Exist!");

        return user.get();
    }

    @Override
    public User addUser(User user) throws Exception {
        logger.debug("The addUser method from UserServiceImpl was called. ");
        Optional<User> u = null;

            u = userRepository.findByUsername(user.getUsername());
            if(u.isPresent()){
                Exception e = new RuntimeException("The username: "+ user.getUsername()+" already exists. It should be unique!");
                errorLogger.error("The transaction was rollback.");
                throw e;
            }else{
                userRepository.save(user);
                logger.debug("User {} was added into DB. ", user.getUsername());
                return user;
            }
    }

    @Override
    public User updateUser(User user) throws Exception {
        logger.debug("The updateUser method from UserServiceImpl was called. ");
        User userUpdated = null;
        Optional<User> userById = userRepository.findByUsername(user.getUsername());
            if (userById.isPresent()) {
                user.setId(userById.get().getId());
                userUpdated = userRepository.save(user);
                logger.debug("User {} was updated into DB. ", userUpdated.getUsername());
            }else{
                Exception e = new UserNotFoundException("The user: "+ user.getUsername() + "does not exist! The transaction was rollback.");
                errorLogger.error("The user: {} was not updated and transaction was rollback.", user.getUsername());
                throw e;
            }
        return userUpdated;
    }

    @Override
    public boolean deleteUser(User user) throws Exception {
        logger.debug("The deleteUser method from UserServiceImpl was called. ");
        Optional<User> userById = userRepository.findByUsername(user.getUsername());
            if (userById.isPresent()) {
                userRepository.delete(userById.get());
                logger.debug("User with id: {} was deleted from DB. ", user.getId());
                return true;
            }else{
                Exception e = new UserNotFoundException("The user: "+ user.getUsername() + "does not exist! The transaction was rollback.");
                errorLogger.error("The user was not deleted and transaction was rollback.", user.getUsername());
                throw e;
            }
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        logger.debug("The getUserByUsername method from UserServiceImpl was called. ");

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            logger.debug("User {} is present on DB: ", user.get().getUsername());
            return user;
        }
            logger.debug("User with username: {} is not present into DB. ", user.get().getUsername());
            return Optional.empty();
    }

}
