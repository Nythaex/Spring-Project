package com.example.demo.services.impl;

import com.example.demo.models.entities.Region;
import com.example.demo.repositories.RegionRepository;
import com.example.demo.services.RegionService;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public void addRegion(Region sofia) {
     regionRepository.save(sofia);
    }

    @Override
    public Region getRegion(String name) {
      return  regionRepository.findByName(name);

    }
}
