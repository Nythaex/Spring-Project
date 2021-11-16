package com.example.demo.services.impl;

import com.example.demo.models.entities.Animal;
import com.example.demo.repositories.AnimalRepository;
import com.example.demo.services.AnimalService;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {
    private AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public void save(Animal animal) {
        animalRepository.save(animal);
    }
}
