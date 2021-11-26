package com.example.demo.models.entities;

import com.example.demo.models.enums.AnimalTypes;

import javax.persistence.*;

@Entity
public class Animal extends BasicEntity{


    private String name;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;
    @JoinColumn()
    @ManyToOne()
    private Shelter shelter;

    private AnimalTypes animalType;

    public Animal() {
        this.image=new Image();
    }

    public Animal(String name, String description, Shelter shelter, AnimalTypes animalType, Image image) {
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

    public Animal setImage(Image image) {
        this.image = image;
        return this;
    }

    public Image getImage() {
        return image;
    }
}
