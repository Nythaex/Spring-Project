package com.example.demo.repositories;

import com.example.demo.models.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town,Long> {
    Town getByName(String town);


    @Query("SELECT t FROM  Town as t ORDER BY t.name")
    List<Town> findAll();
}
