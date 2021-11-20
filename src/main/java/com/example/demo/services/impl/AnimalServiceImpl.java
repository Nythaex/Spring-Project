package com.example.demo.services.impl;

import com.example.demo.models.entities.Animal;
import com.example.demo.models.entities.Worker;
import com.example.demo.models.enums.AnimalTypes;
import com.example.demo.models.view.AnimalView;
import com.example.demo.repositories.AnimalRepository;
import com.example.demo.services.AnimalService;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    @Override
    public void deleteById(Long id) {
        animalRepository.deleteById(id);
    }

    @Override
    public boolean contains(Long id) {
        return animalRepository.findById(id).orElse(null)!=null;
    }

    @Override
    public boolean checkIsItMine(Long id, Long userId) {
       Animal animal = animalRepository.findById(id).orElse(null);
        return Objects.equals(animal.getShelter().getUser().getId(), userId);
    }

    @Override
    public void updateAnimal(AnimalView animalView, Long id) {
        Animal animal = animalRepository.findById(id).orElse(null);

       animal.setImage(animalView.getImage());
       animal.setName(animalView.getName());
       animal.setAnimalType(AnimalTypes.valueOf(animalView.getAnimalType()));
       animal.setDescription(animalView.getDescription());
       animalRepository.save(animal);

    }
}
