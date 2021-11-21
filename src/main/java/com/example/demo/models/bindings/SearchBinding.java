package com.example.demo.models.bindings;

import com.example.demo.models.entities.Town;
import com.example.demo.models.enums.AnimalTypes;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class SearchBinding {
   @NotNull
    private AnimalTypes animalType;
    @Size(min = 1)
    private String town;

    public SearchBinding(AnimalTypes animalType,String town) {
        this.animalType = animalType;
        this.town=town;
    }

    public SearchBinding() {
    }

    public AnimalTypes getAnimalType() {
        return animalType;
    }

    public SearchBinding setAnimalType(AnimalTypes animalType) {
        this.animalType = animalType;
        return this;
    }

    public String getTown() {
        return town;
    }

    public SearchBinding setTown(String town) {
        this.town = town;
        return this;
    }
}
