package com.example.demo.services;

import com.example.demo.models.bindings.AddAnimalBinding;
import com.example.demo.models.bindings.SearchBinding;
import com.example.demo.models.entities.Animal;
import com.example.demo.models.services.AddAnimalService;
import com.example.demo.models.view.AnimalView;

import java.util.List;

public interface AnimalService {


    void deleteBanned();

    Animal getById(Long id);

    void save(Animal animal);

    void deleteById(Long id);

    boolean contains(Long id);

    boolean checkIsItMine(Long id, Long id1);

    void updateAnimal(AddAnimalService addAnimalService, Long id);

    List<AnimalView> getAllSearched(SearchBinding searchBinding);

    AnimalView getAnimalView(Long id);
}
