package com.stady.FirstGradleProject.controllers;

import com.stady.FirstGradleProject.errors.UserNotFoundException;
import com.stady.FirstGradleProject.model.Asset;
import com.stady.FirstGradleProject.model.User;
import com.stady.FirstGradleProject.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Autowired
    private Logger logger;

//     @RequestMapping(value ="/users", method = RequestMethod.GET,
//                    produces = "application/json;charset=UTF-8")
//    Bellow is an alternative to GetMapping and MediaType.APPLICATION_JSON_UTF8_VALUE

    @GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @GetMapping(value = "user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Return an user")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "The 404 message code indicates the fact that the user was not found in DB."),
                    @ApiResponse(code = 200, message = "Successful Operation")
            }
    )
    public ResponseEntity<Resource<User>> getUserById(@RequestParam Long id) {
        User user = userService.getUsersById(id);

        //         Hateoas part
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUsers());
        resource.add(link.withRel("all-users"));

        return ResponseEntity.status(HttpStatus.OK).body(resource);
    }

    @RequestMapping(value = "users/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

//         Hateoas part
//        Resource<User> resource = new Resource<User>(user);
//        ControllerLinkBuilder link = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUsers());
//        resource.add(link.withRel("all-users"));

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping(value = "users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Adding a user on DB")
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
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @DeleteMapping(value = "users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
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



    @GetMapping(value = "/users/{username}/assets", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Return all assets from gradle_db_users.assets table which are related to an user")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful Read Operation"),
                    @ApiResponse(code = 500, message = "Internal server error")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public List<Asset> getAssetsByUser(@PathVariable String username) {
        return userService.getAssetsByUser(username);
    }

    @PostMapping(value = "/users/{username}/assets", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Add a asset by an user on DB.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 500, message = "Internal server error"),
                    @ApiResponse(code = 201, message = "Successful Create Operation")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public Asset addAssetByUser(@PathVariable String username, @Valid @RequestBody Asset asset) {
        return userService.addAssetByUser(username, asset);
    }

}