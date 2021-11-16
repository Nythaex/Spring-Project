package com.example.demo.services.impl;

import com.example.demo.models.entities.Animal;
import com.example.demo.models.entities.Shelter;
import com.example.demo.models.services.AddAnimalService;
import com.example.demo.repositories.ShelterRepository;
import com.example.demo.services.AnimalService;
import com.example.demo.services.ShelterService;
import com.example.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ShelterServiceImpl implements ShelterService {
    private ShelterRepository shelterRepository;
    private UserService userService;
    private ModelMapper modelMapper;
    private AnimalService animalService;

    public ShelterServiceImpl(ShelterRepository shelterRepository, UserService userService, ModelMapper modelMapper, AnimalService animalService) {
        this.shelterRepository = shelterRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.animalService = animalService;
    }


    @Override
    public void addAnimal(AddAnimalService map, Long id) {
        Shelter shelter = userService.getById(id).getShelter();
        Animal animal= modelMapper.map(map, Animal.class).setShelter(shelter);
        animalService.save(animal);
    }
}
