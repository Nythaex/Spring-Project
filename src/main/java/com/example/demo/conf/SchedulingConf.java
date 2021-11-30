package com.example.demo.conf;

import com.example.demo.services.AnimalService;
import com.example.demo.services.WorkerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulingConf {
    private WorkerService workerService;
    private AnimalService animalService;

    public SchedulingConf(WorkerService workerService, AnimalService animalService) {
        this.workerService = workerService;
        this.animalService = animalService;
    }

    @Scheduled(cron = "59 59 23 * * ?")
    public void removeBannedWorkersAndAnimals(){
        workerService.deleteBanned();
        animalService.deleteBanned();
    }


}
