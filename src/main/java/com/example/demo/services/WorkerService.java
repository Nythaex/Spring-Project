package com.example.demo.services;

import com.example.demo.models.entities.Worker;
import com.example.demo.models.services.AddWorkerService;

public interface WorkerService {
    void save(Worker worker);

    void deleteById(Long id);

    void updateWorker(AddWorkerService workerService, Long id);

    boolean checkIsItMine(Long id,Long userId);



    boolean contains(Long id);
}
