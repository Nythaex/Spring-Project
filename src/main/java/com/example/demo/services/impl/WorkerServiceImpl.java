package com.example.demo.services.impl;

import com.example.demo.models.entities.Worker;
import com.example.demo.models.services.AddWorkerService;
import com.example.demo.models.view.WorkerView;
import com.example.demo.repositories.WorkerRepository;
import com.example.demo.services.ImageService;
import com.example.demo.services.WorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final ImageService imageService;
    private final ModelMapper modelMapper;

    public WorkerServiceImpl(WorkerRepository workerRepository, ImageService imageService, ModelMapper modelMapper) {
        this.workerRepository = workerRepository;
        this.imageService = imageService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(Worker worker) {
        workerRepository.save(worker);
    }

    @Override
    public void deleteById(Long id) {
        Worker byId = workerRepository.findById(id).orElse(null);
        imageService.delete(byId.getImage());
        workerRepository.delete(byId);
    }



    @Override
    public boolean checkIsItMine(Long id,Long userId) {
        Worker worker = workerRepository.findById(id).orElse(null);
        return Objects.equals(worker.getShelter().getUser().getId(), userId);

    }

    @Override
    public Worker getById(Long id) {
        return workerRepository.findById(id).orElse(null);
    }

    @Override
    public boolean contains(Long id) {
        return workerRepository.findById(id).orElse(null)!=null;
    }

    @Override
    public WorkerView getWorkerView(Long id) {
        Worker worker = workerRepository.findById(id).orElse(null);
        if (worker==null||worker.getShelter().getUser().getBanned()){
            return null;
        }
        return  modelMapper.map(worker, WorkerView.class).setFullName(worker.getFirstName() + " " + worker.getLastName()).setImage(worker.getImage().getUrl());
    }

    @Override
    public void updateWorker(AddWorkerService workerService, String url, String publicId, Long id) {
        Worker worker = workerRepository.findById(id).orElse(null);

        worker.getImage().setUrl(url).setPublicId(publicId);
        worker.setFirstName(workerService.getFirstName());
        worker.setLastName(workerService.getLastName());
        worker.setDescription(workerService.getDescription());
        workerRepository.save(worker);
    }
}
