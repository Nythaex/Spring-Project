package com.example.demo.models.entities;

import javax.persistence.Entity;

@Entity
public class Image extends BasicEntity{
    private String url;
    private String publicId;

    public Image(String url, String publicId) {
        this.url = url;
        this.publicId = publicId;
    }

    public Image() {
    }

    public String getUrl() {
        return url;
    }

    public Image setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public Image setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }
}
