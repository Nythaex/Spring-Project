package com.example.demo.models.services;

public class SentMessageService {

    private String username;

    private String title;

    private String description;

    public SentMessageService(String username, String title, String description) {
        this.username = username;
        this.title = title;
        this.description = description;
    }

    public SentMessageService() {
    }

    public String getUsername() {
        return username;
    }

    public SentMessageService setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SentMessageService setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SentMessageService setDescription(String description) {
        this.description = description;
        return this;
    }
}
