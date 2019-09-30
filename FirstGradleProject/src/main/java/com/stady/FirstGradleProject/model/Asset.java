package com.stady.FirstGradleProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "Assets")
@ApiModel(description = "All details about an asset")
public class Asset implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "the id of the asset")
    private Long id;

    @NotNull
    @Size(min = 2, max = 30, message = "The name should have alt least 2 characters and maxim 30!")
    @ApiModelProperty(notes = "the name of the asset")
    private String name;

    @Size(min = 2, max = 100, message = "The description should have alt least 2 characters and maxim 100!")
    @ApiModelProperty(notes = "the description of the asset")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore // maybe can cause recursive issue => memory problem
    @ApiModelProperty(notes = "the owner of the asset")
    private User owner;

    public Asset() {
    }

    public Asset(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
//                ", owner=" + owner + // maybe can cause recursive issue => memory problem
                '}';
    }
}
