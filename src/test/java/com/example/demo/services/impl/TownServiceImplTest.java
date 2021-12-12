package com.example.demo.services.impl;

import com.example.demo.models.entities.Town;
import com.example.demo.repositories.TownRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class TownServiceImplTest {

    @Mock
    private TownRepository townRepository;

    private TownServiceImpl townService;
    private Town town;

    @BeforeEach
    void init() {
        town=new Town().setName("Yambol");

        townService=new TownServiceImpl(townRepository);

    }

    @Test
    void test_save(){
        Mockito.when(townRepository.save(Mockito.any(Town.class))).thenAnswer(i -> i.getArguments()[0]);


        townService.addTown(town);
    }
}