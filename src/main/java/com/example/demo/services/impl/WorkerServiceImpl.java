package com.example.demo.services.impl;

import com.example.demo.models.entities.Worker;
import com.example.demo.repositories.WorkerRepository;
import com.example.demo.services.WorkerService;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements WorkerService {
    private WorkerRepository workerRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public void save(Worker worker) {
        workerRepository.save(worker);
    }
}
