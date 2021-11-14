package com.example.demo;

import com.example.demo.services.impl.InitServiceImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements ApplicationRunner {
    private InitServiceImpl initServiceImpl;

    public Runner(InitServiceImpl initServiceImpl) {
        this.initServiceImpl = initServiceImpl;
    }


    @Override
    public void run(ApplicationArguments args){
        initServiceImpl.init();
    }


}
