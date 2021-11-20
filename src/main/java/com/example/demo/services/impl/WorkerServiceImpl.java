package com.example.demo.services.impl;

import com.example.demo.models.entities.Worker;
import com.example.demo.models.services.AddWorkerService;
import com.example.demo.repositories.WorkerRepository;
import com.example.demo.services.WorkerService;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    @Override
    public void deleteById(Long id) {
        workerRepository.deleteById(id);
    }

    @Override
    public void updateWorker(AddWorkerService workerService, Long id) {
        Worker worker = workerRepository.findById(id).orElse(null);

        worker.setImage(workerService.getImage());
        worker.setFirstName(workerService.getFirstName());
        worker.setLastName(workerService.getLastName());
        worker.setDescription(workerService.getDescription());
        workerRepository.save(worker);

    }

    @Override
    public boolean checkIsItMine(Long id,Long userId) {
        Worker worker = workerRepository.findById(id).orElse(null);
        return Objects.equals(worker.getShelter().getUser().getId(), userId);

    }

    @Override
    public boolean contains(Long id) {
        return workerRepository.findById(id).orElse(null)!=null;
    }
}
