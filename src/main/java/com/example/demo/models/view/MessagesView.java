package com.example.demo.models.view;

public class MessagesView {

    private Long id;
    private String title;
    private String description;
    private String from;
    private String to;

    public MessagesView(String title, String description, String from, String to, Long id) {
        this.title = title;
        this.description = description;
        this.from = from;
        this.to = to;
        this.id=id;
    }

    public MessagesView() {
    }

    public String getTitle() {
        return title;
    }

    public MessagesView setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MessagesView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public MessagesView setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public MessagesView setTo(String to) {
        this.to = to;
        return this;
    }

    public Long getId() {
        return id;
    }

    public MessagesView setId(Long id) {
        this.id = id;
        return this;
    }
}
