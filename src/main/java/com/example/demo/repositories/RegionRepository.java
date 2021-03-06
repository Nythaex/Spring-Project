package com.example.demo.repositories;

import com.example.demo.models.entities.Region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long> {
    Region findByName(String name);
}
