package com.example.demo.services;

import com.example.demo.models.services.AddAnimalService;
import com.example.demo.models.services.AddWorkerService;

public interface ShelterService {


    void addAnimal(AddAnimalService animal, Long id);

    void addWorker(AddWorkerService worker, Long id);
}
