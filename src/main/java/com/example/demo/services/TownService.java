package com.example.demo.services;

import com.example.demo.models.entities.Town;

import java.util.List;

public interface TownService {
    void addTown(Town sofia);

    boolean isEmpty();

    Town getByName(String town);

    List<String> getAll();

}
