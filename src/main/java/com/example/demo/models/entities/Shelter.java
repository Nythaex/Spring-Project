package com.example.demo.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Shelter extends BasicEntity{

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private String name;

    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;


    @OneToMany(fetch = FetchType.EAGER,mappedBy = "shelter")
    private Set<Animal> animals;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "shelter")
    private Set<Worker> workers;

    @OneToOne(fetch = FetchType.EAGER,mappedBy = "shelter")
    private User user;

    public Shelter() {
        this.workers=new HashSet<>();
        this.animals=new HashSet<>();
    }

    public Shelter(String description, String name, String imageUrl) {
        this.workers=new HashSet<>();
        this.animals=new HashSet<>();
        this.description = description;
        this.name = name;
       this.image =imageUrl;
    }

    public String getName() {
        return name;
    }

    public Shelter setName(String name) {
        this.name = name;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Shelter setUser(User user) {
        this.user = user;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Shelter setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public Shelter setAnimals(Set<Animal> animals) {
        this.animals = animals;
        return this;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public Shelter setWorkers(Set<Worker> workers) {
        this.workers = workers;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Shelter setImage(String image) {
        this.image = image;
        return this;
    }
}
