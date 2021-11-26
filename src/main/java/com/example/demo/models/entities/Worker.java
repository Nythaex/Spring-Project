package com.example.demo.models.entities;

import javax.persistence.*;

@Entity
public class Worker extends BasicEntity{


    private String firstName;
    private String lastName;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @JoinColumn(nullable = false)
    @ManyToOne()
    private Shelter shelter;

    public Worker() {
        this.image=new Image();
    }

    public Worker(String firstName, String lastName, String description, Image image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.image = image;
        this.image=new Image();
    }

    public String getFirstName() {
        return firstName;
    }

    public Worker setFirstName(String name) {
        this.firstName = name;
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

    public Image getImage() {
        return image;
    }

    public Worker setImage(Image image) {
        this.image = image;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Worker setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
