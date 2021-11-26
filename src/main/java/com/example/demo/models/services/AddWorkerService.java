package com.example.demo.models.services;

public class AddWorkerService {

    private String firstName;
    private String lastName;


    private String description;



    public AddWorkerService(String firstName, String lastName, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
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


    public String getLastName() {
        return lastName;
    }

    public AddWorkerService setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
