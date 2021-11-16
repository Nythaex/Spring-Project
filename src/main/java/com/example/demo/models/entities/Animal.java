package com.example.demo.models.entities;

import com.example.demo.models.enums.AnimalTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Animal extends BasicEntity{


    private String name;
    private String description;
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @JoinColumn()
    @ManyToOne()
    private Shelter shelter;

    private AnimalTypes animalType;

    public Animal() {
    }

    public Animal(String name, String description, Shelter shelter,AnimalTypes animalType,String image) {
        this.name = name;
        this.description = description;
        this.shelter = shelter;
        this.animalType=animalType;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public Animal setImage(String image) {
        this.image = image;
        return this;
    }
}
