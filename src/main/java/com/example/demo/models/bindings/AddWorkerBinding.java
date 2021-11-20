package com.example.demo.models.bindings;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddWorkerBinding {
    @NotBlank
    @Size(min = 1,max = 10)
    private String firstName;
    @NotBlank
    @Size(min = 1,max = 10)
    private String lastName;


    private String description;

    @NotNull
    private MultipartFile image;

    public AddWorkerBinding(String firstName, String lastName, String description, MultipartFile image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.image = image;
    }

    public AddWorkerBinding() {
    }

    public String getFirstName() {
        return firstName;
    }

    public AddWorkerBinding setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddWorkerBinding setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public AddWorkerBinding setImage(MultipartFile image) {
        this.image = image;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AddWorkerBinding setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
