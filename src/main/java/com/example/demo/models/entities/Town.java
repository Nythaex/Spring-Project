package com.example.demo.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Town extends BasicEntity{
    @Column(unique = true,nullable = false)
    private String name;
    @ManyToOne
    private Region region;


    public Town() {
    }

    public Town(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public Town setName(String name) {
        this.name = name;
        return this;
    }




    public Region getRegion() {
        return region;
    }

    public Town setRegion(Region region) {
        this.region = region;
        return this;
    }
}
