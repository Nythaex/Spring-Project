package com.example.demo.services.impl;

import com.example.demo.models.entities.Town;
import com.example.demo.repositories.TownRepository;
import com.example.demo.services.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
    }

    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository=townRepository;
        modelMapper = null;
    }


    @Override
    public void addTown(Town sofia) {
      townRepository.save(sofia);
    }

    @Override
    public boolean isEmpty() {
        return townRepository.count()==0;
    }

    @Override
    public Town getByName(String town) {
        return townRepository.getByName(town);
    }

    @Override
    public List<String> getAll() {
        return townRepository.findAll().stream().map(s->modelMapper.map(s.getName(),String.class)).collect(Collectors.toList());
    }
}
