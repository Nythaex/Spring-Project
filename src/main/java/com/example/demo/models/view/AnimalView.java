package com.example.demo.models.view;

public class AnimalView {
    private Long userId;
    private Long id;
    private String name;
    private String description;
    private String image;
    private String animalType;

    public AnimalView(Long id) {
        this.id = id;
    }

    public AnimalView() {
    }

    public AnimalView(Long userId, Long id, String name, String description, String image, String animalType) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.animalType = animalType;
    }

    public Long getId() {
        return id;
    }

    public AnimalView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AnimalView setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AnimalView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public AnimalView setImage(String image) {
        this.image = image;
        return this;
    }

    public String getAnimalType() {
        return animalType;
    }

    public AnimalView setAnimalType(String animalType) {
        this.animalType = animalType;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public AnimalView setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
