package com.example.demo.repositories;

import com.example.demo.models.entities.Region;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface RegionRepository extends JpaRepository<Region,Long> {
    Region findByName(String name);
}
