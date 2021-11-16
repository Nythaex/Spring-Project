package com.example.demo.models.bindings;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddShelterBinding {


    private String description;

    @NotBlank
    @Size(min = 1)
    private String name;

    @NotNull
    private MultipartFile image;

    public AddShelterBinding(String description, String name, MultipartFile image) {
        this.description = description;
        this.name = name;
        this.image = image;
    }

    public AddShelterBinding() {
    }

    public String getDescription() {
        return description;
    }

    public AddShelterBinding setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddShelterBinding setName(String name) {
        this.name = name;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public AddShelterBinding setImage(MultipartFile image) {
        this.image = image;
        return this;
    }
}
