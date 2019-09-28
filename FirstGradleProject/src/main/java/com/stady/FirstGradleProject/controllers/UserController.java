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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("rest")
@Api(value = "UserController", description = "This Resource is used for CRUD services")
public class UserController {

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
                    @ApiResponse(code = 200, message = "Successful Read Operation"),
                    @ApiResponse(code = 500, message = "Internal server error")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return (ArrayList<User>) userService.getUsers();
    }


    @GetMapping(value = "user", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Return an user")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "The 404 message code indicates the fact that the user was not found in DB."),
                    @ApiResponse(code = 200, message = "Successful Operation")
            }
    )
    public User getUserById(@RequestParam Long id) {
        logger.debug("User Id : {}", id);
        return userService.getUsersById(id);
    }

    @PostMapping(value = "users", consumes = "application/json;charset=UTF-8")
    @ApiOperation(value = "Return a HttpStatus message")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 501, message = "The 501 message code indicates the fact that the user was not stored in DB."),
                    @ApiResponse(code = 201, message = "Successful Create Operation")
            }
    )
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) throws Exception {
        logger.debug("User details : {}", user);
        User u = userService.addUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @PutMapping(value = "users",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Update a user from gradle_db_users.Users table")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "The user does not exist."),
                    @ApiResponse(code = 200, message = "Successful Update Operation")
            }
    )
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) throws Exception {
        logger.debug("User for update : {}", user);
        User u = userService.updateUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(u);
    }

    @DeleteMapping(value = "users", consumes = "application/json;charset=UTF-8")
    @ApiOperation(value = "Return a HttpStatus message")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 304, message = "The 304 message code indicates the fact the user was not deleted from DB."),
                    @ApiResponse(code = 404, message = "The user does not exist on DB"),
                    @ApiResponse(code = 200, message = "Successful Delete Operation")
            }
    )

    public ResponseEntity<User> deleteUser(@Valid @RequestBody User user) throws Exception {
        logger.debug("User details : {} with the id: {}", user, user.getId());
        boolean isDeleted = userService.deleteUser(user);

        return (isDeleted ? ResponseEntity.status(HttpStatus.OK).body(user) :  ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(user));
    }


    @RequestMapping(value = "user/{username}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "Return an user by username from gradle_db_users.Users table")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "The username does not exist on DB"),
                    @ApiResponse(code = 200, message = "Successful Delete Operation")
            }
    )
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) throws UserNotFoundException {
        logger.debug("Get user from Controller by username : {}", username);
        User user = userService.getUserByUsername(username);

        return ResponseEntity.status(HttpStatus.OK).body(user);
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