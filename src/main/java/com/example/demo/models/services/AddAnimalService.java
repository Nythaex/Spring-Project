package com.example.demo.models.services;

import com.example.demo.models.enums.AnimalTypes;

public class AddAnimalService {
    private String name;


    private String description;


    private String imageUrl;
    private String imageId;



    private AnimalTypes animalType;

    public AddAnimalService(String name, String description, String imageUrl, String imageId, AnimalTypes animalType) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
        this.animalType = animalType;
    }

    public AddAnimalService() {
    }

    public String getName() {
        return name;
    }

    public AddAnimalService setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddAnimalService setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageId() {
        return imageId;
    }

    public AddAnimalService setImageId(String imageId) {
        this.imageId = imageId;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddAnimalService setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public AnimalTypes getAnimalType() {
        return animalType;
    }

    public AddAnimalService setAnimalType(AnimalTypes animalType) {
        this.animalType = animalType;
        return this;
    }
}
