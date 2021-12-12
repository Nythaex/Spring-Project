package com.example.demo.services.impl;

import com.example.demo.models.entities.Region;
import com.example.demo.models.entities.Town;
import com.example.demo.repositories.RegionRepository;
import com.example.demo.services.RegionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegionServiceImplTest {

    @Mock
    private   RegionRepository regionRepository;

    private RegionService regionService;
    private Region region;

    @BeforeEach
    void init(){
        regionService=new RegionServiceImpl(regionRepository);
        region=new Region().setName("Pazar");
    }

    @Test
    void test_save(){
        Mockito.when(regionRepository.save(Mockito.any(Region.class))).thenAnswer(i -> i.getArguments()[0]);
       regionService.addRegion(new Region().setName("Shumensko"));

    }

    @Test
    void test_getRegion(){
        Mockito.when(regionRepository.findByName(region.getName())).thenReturn(region);
      assertEquals(region.getName(),regionService.getRegion("Pazar").getName());

    }
}