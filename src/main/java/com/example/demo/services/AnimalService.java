package com.example.demo.services;

import com.example.demo.models.entities.Animal;
import com.example.demo.models.view.AnimalView;

public interface AnimalService {
    void save(Animal animal);

    void deleteById(Long id);

    boolean contains(Long id);

    boolean checkIsItMine(Long id, Long id1);

    void updateAnimal(AnimalView setImage, Long id);
}
