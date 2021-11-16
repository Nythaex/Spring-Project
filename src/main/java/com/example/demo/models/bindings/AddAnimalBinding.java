package com.example.demo.models.bindings;

import com.example.demo.models.enums.AnimalTypes;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddAnimalBinding {

    @NotBlank
    @Size(min = 1)
    private String name;


    private String description;

    @NotNull
    private MultipartFile image;


    @NotNull
    private AnimalTypes animalType;

    public AddAnimalBinding(String name, String description, MultipartFile image, AnimalTypes animalType) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.animalType = animalType;
    }

    public AddAnimalBinding() {
    }

    public String getName() {
        return name;
    }

    public AddAnimalBinding setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddAnimalBinding setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public AddAnimalBinding setImage(MultipartFile image) {
        this.image = image;
        return this;
    }

    public AnimalTypes getAnimalType() {
        return animalType;
    }

    public AddAnimalBinding setAnimalType(AnimalTypes animalType) {
        this.animalType = animalType;
        return this;
    }
}
