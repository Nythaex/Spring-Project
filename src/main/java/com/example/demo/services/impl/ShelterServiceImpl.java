package com.example.demo.services.impl;

import com.example.demo.models.entities.Shelter;
import com.example.demo.repositories.ShelterRepository;
import com.example.demo.services.ShelterService;
import org.springframework.stereotype.Service;

@Service
public class ShelterServiceImpl implements ShelterService {
    private ShelterRepository shelterRepository;

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }


}
