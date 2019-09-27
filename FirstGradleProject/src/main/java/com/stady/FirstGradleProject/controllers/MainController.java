package com.stady.FirstGradleProject.controllers;

import com.stady.FirstGradleProject.errors.UserNotFoundException;
import com.stady.FirstGradleProject.model.User;
import com.stady.FirstGradleProject.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("rest")
@Api(value = "MainController", description = "This Resource is used for CRUD services")
public class MainController {

    @Autowired
    private UserService userService;

    @Value("${app.message}")
    private String welcomeMessage;

    @Autowired
    private Logger logger;

//     @RequestMapping(value ="/users", method = RequestMethod.GET,
//                    produces = "application/json;charset=UTF-8")

    @GetMapping(value = "users", produces = "application/json;charset=UTF-8")
//    @ResponseBody
    @ApiOperation(value = "Return all users from gradle_db_users.Users table")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful Read Operation")
            }
    )
    public List<User> getUsers() {
        return (ArrayList<User>) userService.getUsers();
    }


    @GetMapping(value = "user", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Return an user")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "The 404 message code indicates the fact that the user was not found in DB."),
                    @ApiResponse(code = 200, message = "Successful Create Operation")
            }
    )
    public User getUserById(@RequestParam Long id) {
        logger.debug("User Id : {}", id);
        return userService.getUsersById(id);
    }

    @PostMapping(value = "users", consumes = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Return a HttpStatus message")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 501, message = "The 501 message code indicates the fact that the user was not stored in DB."),
                    @ApiResponse(code = 200, message = "Successful Create Operation")
            }
    )
    public User addUser(@RequestBody User user) throws Exception {
        logger.debug("User details : {}", user);
        User u = userService.addUser(user);

        return u;
    }

    @PutMapping(value = "users",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a user from gradle_db_users.Users table")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "The user does not exist."),
                    @ApiResponse(code = 200, message = "Successful Update Operation")
            }
    )
    public User updateUser(@RequestBody User user) throws Exception {
        logger.debug("User for update : {}", user);
        return userService.updateUser(user);
    }

    @DeleteMapping(value = "users", consumes = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Return a HttpStatus message")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 501, message = "The 501 message code indicates the fact the user was not deleted from DB."),
                    @ApiResponse(code = 404, message = "The user does not exist on DB"),
                    @ApiResponse(code = 200, message = "Successful Create Operation")
            }
    )

    public HttpStatus deleteUser(@RequestBody User user) throws Exception {
        logger.debug("User details : {} with the id: {}", user, user.getId());
        boolean isDeleted = userService.deleteUser(user);

        return (isDeleted ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED);
    }
    @RequestMapping(value = "user/{username}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "Return an user by username from gradle_db_users.Users table")
    public User getUserByUsername(@PathVariable String username){
        logger.debug("Get user from Controller by username : {}", username);
        User user = userService.getUserByUsername(username).get();
        return user;
    }

    @GetMapping(value = "welcome", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Return the environment message")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful Read Operation")
            }
    )
    public String getEnvironmentMessage() {
        return welcomeMessage;
    }
}