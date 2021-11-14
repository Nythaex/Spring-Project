package com.example.demo.models.entities;

import com.example.demo.models.enums.AnimalTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Animal extends BasicEntity{

    @Column(nullable = false,unique = true)
    private String name;
    private String description;
    private String imageUrl;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Shelter shelter;

    private AnimalTypes animalType;

    public Animal() {
    }

    public Animal(String name, String description, Shelter shelter,AnimalTypes animalType,String imageUrl) {
        this.name = name;
        this.description = description;
        this.shelter = shelter;
        this.animalType=animalType;
        this.imageUrl=imageUrl;
    }

    public String getName() {
        return name;
    }

    public Animal setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Animal setDescription(String description) {
        this.description = description;
        return this;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public Animal setShelter(Shelter shelter) {
        this.shelter = shelter;
        return this;
    }

    public AnimalTypes getAnimalType() {
        return animalType;
    }

    public Animal setAnimalType(AnimalTypes animalType) {
        this.animalType = animalType;
        return this;
    }
}
