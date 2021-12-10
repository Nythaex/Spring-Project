package com.example.demo.services.impl;

import com.example.demo.models.view.StatsView;
import com.example.demo.services.StatService;
import org.springframework.stereotype.Service;

@Service
public class StatServiceImpl implements StatService {

    private Long requests=0L;



    @Override
    public void onRequest() {
        requests++;
    }

    @Override
    public StatsView getStats() {
        return new StatsView(requests);
    }
}
