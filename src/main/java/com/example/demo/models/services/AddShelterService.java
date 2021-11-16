package com.example.demo.models.services;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddShelterService {
    private String description;

    private String name;


    private String image;

    public AddShelterService(String description, String name, String image) {
        this.description = description;
        this.name = name;
        this.image = image;
    }

    public AddShelterService() {
    }

    public String getDescription() {
        return description;
    }

    public AddShelterService setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddShelterService setName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public AddShelterService setImage(String image) {
        this.image = image;
        return this;
    }
}
