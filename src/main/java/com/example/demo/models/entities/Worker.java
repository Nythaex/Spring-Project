package com.example.demo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Worker extends BasicEntity{


    private String firstName;
    private String lastName;
    private String description;
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Shelter shelter;

    public Worker() {
    }

    public Worker(String firstName, String lastName, String description, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public Worker setFirstName(String name) {
        this.firstName = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Worker setDescription(String description) {
        this.description = description;
        return this;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public Worker setShelter(Shelter shelter) {
        this.shelter = shelter;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Worker setImage(String imageUrl) {
        this.image = imageUrl;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Worker setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
