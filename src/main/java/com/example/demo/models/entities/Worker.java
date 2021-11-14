package com.example.demo.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Worker extends BasicEntity{

    @Column(nullable = false,unique = true)
    private String name;
    private String description;
    private String imageUrl;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Shelter shelter;

    public Worker() {
    }

    public Worker(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl=imageUrl;
    }

    public String getName() {
        return name;
    }

    public Worker setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Worker setDescription(String description) {
        this.description = description;
        return this;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public Worker setShelter(Shelter shelter) {
        this.shelter = shelter;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Worker setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
