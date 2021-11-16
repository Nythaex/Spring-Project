package com.example.demo.models.view;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ShelterView {

    private String description;
    private String name;
    private String image;
    private String username;
    private List<AnimalView> animals;

    public ShelterView(String description, String name, String image, String username) {
        this.description = description;
        this.name = name;
        this.image = image;
        this.username = username;
    }

    public ShelterView() {
    }

    public String getDescription() {
        return description;
    }

    public ShelterView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShelterView setName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ShelterView setImage(String image) {
        this.image = image;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ShelterView setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<AnimalView> getAnimals() {
        return animals;
    }

    public ShelterView setAnimals(List<AnimalView> animals) {
        this.animals = animals;
        return this;
    }
}
