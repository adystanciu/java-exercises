package com.stady.FirstGradleProject.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "Users")
@ApiModel(description = "All details about the user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "the id of the user")
    private Long id;

    @NotNull
    @Size(min = 4, max = 30, message = "The username should have alt least 4 characters and maxim 30!")
    @ApiModelProperty(notes = "the username")
    private String username;

    @NotNull
    @Size(min = 4, max = 30, message = "The password should have alt least 4 characters and maxim 30!")
    @ApiModelProperty(notes = "the password of the user")
    private String password;

    @NotNull
    @Size(min = 4, max = 50, message = "The email should have alt least 4 characters and maxim 50!")
    @ApiModelProperty(notes = "the email of the user")
    private String email;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
