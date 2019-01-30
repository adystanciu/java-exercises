package com.stady.FirstGradleProject.controllers;

import com.stady.FirstGradleProject.model.User;
import com.stady.FirstGradleProject.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Logger logger;

//     @RequestMapping(value ="/users", method = RequestMethod.GET,
//                    produces = "application/json;charset=UTF-8")

    @GetMapping(value = "users",produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "Return all users from gradle_db_users.Users table")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful Read Operation")
            }
    )
    public List<User> getUsers(){
        return (ArrayList<User>) userService.getUsers();
    }


    @PostMapping(value = "users",consumes = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Return a HttpStatus message")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 501, message = "The 501 message code indicates the fact the user was not stored in DB."),
                    @ApiResponse(code = 200, message = "Successful Create Operation")
            }
    )
    public HttpStatus addUser(@RequestBody User user){
        logger.debug("User details : {}", user);
        User u = userService.addUser(user);

        if(u == null) return HttpStatus.NOT_IMPLEMENTED;
        else return HttpStatus.OK;
    }

    @PutMapping(value ="users",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a user from gradle_db_users.Users table")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful Update Operation")
            }
    )
    public User updateUser(@RequestBody User user){
        logger.debug("User for update : {}", user);
        return userService.updateUser(user);
    }
}
