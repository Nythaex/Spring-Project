package com.example.demo.models.entities;

import javax.persistence.*;

@Entity
public class Message extends BasicEntity{

    @Column(nullable = false)
    private String title;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private User from;
    @ManyToOne()
    @JoinColumn(nullable = false)
    private User to;


    public Message() {
    }

    public Message(String title, String description, User from,User to) {
        this.title = title;
        this.description = description;
        this.from=from;
        this.to=to;

    }

    public String getTitle() {
        return title;
    }

    public Message setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Message setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getFrom() {
        return from;
    }

    public Message setFrom(User from) {
        this.from = from;
        return this;
    }

    public User getTo() {
        return to;
    }

    public Message setTo(User to) {
        this.to = to;
        return this;
    }
}
