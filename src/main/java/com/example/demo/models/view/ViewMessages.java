package com.example.demo.models.view;

public class ViewMessages {

    private Long id;
    private String title;
    private String description;
    private String from;
    private String to;

    public ViewMessages(String title, String description, String from, String to, Long id) {
        this.title = title;
        this.description = description;
        this.from = from;
        this.to = to;
        this.id=id;
    }

    public ViewMessages() {
    }

    public String getTitle() {
        return title;
    }

    public ViewMessages setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ViewMessages setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public ViewMessages setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public ViewMessages setTo(String to) {
        this.to = to;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ViewMessages setId(Long id) {
        this.id = id;
        return this;
    }
}
