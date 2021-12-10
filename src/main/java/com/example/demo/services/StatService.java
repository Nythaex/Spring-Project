package com.example.demo.services;

import com.example.demo.models.view.StatsView;

public interface StatService {

    public void onRequest();
   public StatsView getStats();
}
