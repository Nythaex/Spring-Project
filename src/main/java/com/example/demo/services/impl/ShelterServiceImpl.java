package com.example.demo.services.impl;

import com.example.demo.models.entities.Animal;
import com.example.demo.models.entities.Shelter;
import com.example.demo.models.entities.Worker;
import com.example.demo.models.services.AddAnimalService;
import com.example.demo.models.services.AddWorkerService;
import com.example.demo.repositories.ShelterRepository;
import com.example.demo.services.AnimalService;
import com.example.demo.services.ShelterService;
import com.example.demo.services.UserService;
import com.example.demo.services.WorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ShelterServiceImpl implements ShelterService {
    private ShelterRepository shelterRepository;
    private UserService userService;
    private ModelMapper modelMapper;
    private AnimalService animalService;
    private WorkerService workerService;

    public ShelterServiceImpl(ShelterRepository shelterRepository, UserService userService, ModelMapper modelMapper, AnimalService animalService, WorkerService workerService) {
        this.shelterRepository = shelterRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.animalService = animalService;
        this.workerService = workerService;
    }


    @Override
    public void addAnimal(AddAnimalService map, Long id) {
        Shelter shelter = userService.getById(id).getShelter();
        Animal animal= modelMapper.map(map, Animal.class).setShelter(shelter);
        animalService.save(animal);
    }

    @Override
    public void addWorker(AddWorkerService map, Long id) {
        Shelter shelter = userService.getById(id).getShelter();
        Worker worker= modelMapper.map(map, Worker.class).setShelter(shelter);
        workerService.save(worker);
    }
}
