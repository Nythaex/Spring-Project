package com.example.demo.models.view;

public class WorkerView {
    private Long id;
    private String fullName;
    private String description;
    private String image;

    public WorkerView(Long id, String fullName, String description, String image) {
        this.id = id;
        this.fullName = fullName;
        this.description = description;
        this.image = image;
    }

    public WorkerView() {
    }

    public Long getId() {
        return id;
    }

    public WorkerView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public WorkerView setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WorkerView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public WorkerView setImage(String image) {
        this.image = image;
        return this;
    }
}
