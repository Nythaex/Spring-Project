package com.example.demo.models.services;

import com.example.demo.models.enums.AnimalTypes;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class AddAnimalService {
    private String name;


    private String description;


    private String image;



    private AnimalTypes animalType;

    public AddAnimalService(String name, String description, String image, AnimalTypes animalType) {
        this.name = name;
        this.description = description;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public AddAnimalService setImage(String image) {
        this.image = image;
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
