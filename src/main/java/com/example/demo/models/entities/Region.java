package com.example.demo.models.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Region extends BasicEntity{
    private String name;
    @OneToMany(mappedBy = "region")
    private Set<Town> towns;

    public Region() {
        this.towns=new HashSet<>();

    }

    public Region(String name, Set<Town> towns) {
        this.name = name;
        this.towns = towns;
    }

    public String getName() {
        return name;
    }

    public Region setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Town> getTowns() {
        return towns;
    }

    public Region setTowns(Set<Town> towns) {
        this.towns = towns;
        return this;
    }
}
