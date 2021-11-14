package com.example.demo.models.bindings;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SendMessageBinding {
    @Size(min = 4,max = 20)
    @NotBlank
    private String username;
    @Size(min = 4,max = 20)
    @NotBlank
    private String title;
    @Size(min = 10)
    private String description;

    public SendMessageBinding(String username, String title, String description) {
        this.username = username;
        this.title = title;
        this.description = description;
    }

    public SendMessageBinding() {
    }

    public String getUsername() {
        return username;
    }

    public SendMessageBinding setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SendMessageBinding setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SendMessageBinding setDescription(String description) {
        this.description = description;
        return this;
    }
}
