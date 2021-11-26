package com.example.demo.models.services;

public class AddShelterService {
    private String description;

    private String name;


    private String imageUrl;
    private String imageId;

    public AddShelterService(String description, String name, String imageUrl, String imageId) {
        this.description = description;
        this.name = name;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public AddShelterService setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getImageId() {
        return imageId;
    }

    public AddShelterService setImageId(String imageId) {
        this.imageId = imageId;
        return this;
    }
}
