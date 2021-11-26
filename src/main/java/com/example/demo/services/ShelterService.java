package com.example.demo.services;

import com.example.demo.models.services.AddAnimalService;
import com.example.demo.models.services.AddWorkerService;

public interface ShelterService {



    void addWorker(AddWorkerService map, String url, String publicId, Long id);

    void addAnimal(AddAnimalService map, String url, String publicId, Long id);
}
