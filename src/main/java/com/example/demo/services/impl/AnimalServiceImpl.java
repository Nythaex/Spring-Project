package com.example.demo.services.impl;

import com.example.demo.models.bindings.SearchBinding;
import com.example.demo.models.entities.Animal;
import com.example.demo.models.enums.AnimalTypes;
import com.example.demo.models.view.AnimalView;
import com.example.demo.repositories.AnimalRepository;
import com.example.demo.services.AnimalService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AnimalServiceImpl implements AnimalService {
    private AnimalRepository animalRepository;
    private ModelMapper modelMapper;

    public AnimalServiceImpl(AnimalRepository animalRepository, ModelMapper modelMapper) {
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
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

    @Override
    public List<AnimalView> getAllSearched(SearchBinding searchBinding) {
       return animalRepository.findAll().stream().filter(a->a.getAnimalType()==searchBinding.getAnimalType()&&a.getShelter().getUser().getTown().getName().equals(searchBinding.getTown())).map(a->modelMapper.map(a,AnimalView.class).setUserId(a.getShelter().getUser().getId())).toList();

    }
}
