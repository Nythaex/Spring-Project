package com.example.demo.models.view;

public class StatsView {
    private Long requests=0L;

    public StatsView(Long requests) {
        this.requests = requests;
    }

    public Long getRequests() {
        return requests;
    }

    public StatsView setRequests(Long requests) {
        this.requests = requests;
        return this;
    }
}
