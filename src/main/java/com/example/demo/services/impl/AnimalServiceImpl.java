package com.example.demo.services.impl;

import com.example.demo.models.bindings.SearchBinding;
import com.example.demo.models.entities.Animal;
import com.example.demo.models.enums.AnimalTypes;
import com.example.demo.models.services.AddAnimalService;
import com.example.demo.models.view.AnimalView;
import com.example.demo.repositories.AnimalRepository;
import com.example.demo.services.AnimalService;
import com.example.demo.services.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;
    private final ImageService imageService;

    public AnimalServiceImpl(AnimalRepository animalRepository, ModelMapper modelMapper, ImageService imageService) {
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
        this.imageService = imageService;
    }


    @Override
    public Animal getById(Long id){
     return    animalRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public void deleteById(Long id) {
        Animal byId = animalRepository.findById(id).orElse(null);
        imageService.delete(byId.getImage());
        animalRepository.delete(byId);
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
    public void updateAnimal(AddAnimalService addAnimalService, Long id) {
        Animal animal = animalRepository.findById(id).orElse(null);

        animal.getImage().setUrl(addAnimalService.getImageUrl()).setPublicId(addAnimalService.getImageId());
       animal.setName(addAnimalService.getName());
       animal.setAnimalType(AnimalTypes.valueOf(addAnimalService.getAnimalType().name()));
       animal.setDescription(addAnimalService.getDescription());
       animalRepository.save(animal);

    }

    @Override
    public List<AnimalView> getAllSearched(SearchBinding searchBinding) {
       return animalRepository.findAll().stream().filter(a->a.getAnimalType()==searchBinding.getAnimalType()&&a.getShelter().getUser().getTown().getName().equals(searchBinding.getTown())).map(a->getAnimalView(a.getId()).setUserId(a.getShelter().getUser().getId())).toList();

    }

    @Override
    public AnimalView getAnimalView(Long id) {
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal==null){
            return null;
        }

        return    modelMapper.map(animal,AnimalView.class).setImage(animal.getImage().getUrl());
    }
}
