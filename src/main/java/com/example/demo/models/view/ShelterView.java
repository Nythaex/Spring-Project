package com.example.demo.models.view;

import java.util.LinkedList;
import java.util.List;

public class ShelterView {

    private Long id;
    private String description;
    private String name;
    private String image;
    private String username;
    private LinkedList<AnimalView> animals;
    private List<WorkerView> workers;

    public ShelterView(Long id, String description, String name, String image, String username, LinkedList<WorkerView> workers) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.image = image;
        this.username = username;
        this.workers = workers;
    }

    public ShelterView() {
    }

    public ShelterView(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public ShelterView setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ShelterView setId(Long id) {
        this.id = id;
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

    public ShelterView setAnimals(LinkedList<AnimalView> animals) {
        this.animals = animals;
        return this;
    }

    public List<WorkerView> getWorkers() {
        return workers;
    }

    public ShelterView setWorkers(List<WorkerView> workers) {
        this.workers = workers;
        return this;
    }
}
