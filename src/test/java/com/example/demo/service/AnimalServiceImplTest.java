package com.example.demo.service;

import com.example.demo.models.entities.Animal;
import com.example.demo.models.enums.AnimalTypes;
import com.example.demo.repositories.AnimalRepository;
import com.example.demo.services.impl.AnimalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceImplTest {

    private  AnimalServiceImpl animalService;
     @Mock
    private  AnimalRepository animalRepository;

    @BeforeEach
    void init(){




    }


}
