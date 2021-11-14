package com.example.demo.models.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BasicEntity(Long id) {
        this.id = id;
    }

    public BasicEntity() {
    }

    public Long getId() {
        return id;
    }

    public BasicEntity setId(Long id) {
        this.id = id;
        return this;
    }
}
