package com.example.demo.models.services;

import org.springframework.web.multipart.MultipartFile;

public class AddWorkerService {

    private String firstName;
    private String lastName;


    private String description;

    private String image;

    public AddWorkerService(String firstName, String lastName, String description, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.image = image;
    }

    public AddWorkerService() {
    }

    public String getFirstName() {
        return firstName;
    }

    public AddWorkerService setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddWorkerService setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public AddWorkerService setImage(String image) {
        this.image = image;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AddWorkerService setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
