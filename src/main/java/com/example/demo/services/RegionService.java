package com.example.demo.services;

import com.example.demo.models.entities.Region;

public interface RegionService {
    void addRegion(Region sofia);
    Region getRegion(String region);

}
