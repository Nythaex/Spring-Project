package com.example.demo.services;

import com.example.demo.models.entities.Worker;
import com.example.demo.models.services.AddWorkerService;
import com.example.demo.models.view.WorkerView;

public interface WorkerService {
    void save(Worker worker);

    void deleteById(Long id);

    boolean checkIsItMine(Long id,Long userId);

    Worker getById(Long id);

    boolean contains(Long id);

    WorkerView getWorkerView(Long id);

    void updateWorker(AddWorkerService map, String url, String publicId, Long id);
}
