package com.example.demo.models.view;

public class UserView {
    private Long id;
    private String username;
    private String email;
    private String town;
    private Boolean registeredAsShelter;
    private Boolean isBanned;

    public UserView() {
    }

    public UserView(String username, String email, String town, Boolean registeredAsShelter, Long id,Boolean isBanned) {
        this.username = username;
        this.email = email;
        this.town = town;
        this.registeredAsShelter = registeredAsShelter;
        this.id=id;
          this.isBanned = isBanned;
    }

    public String getUsername() {
        return username;
    }

    public UserView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserView setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTown() {
        return town;
    }

    public UserView setTown(String town) {
        this.town = town;
        return this;
    }

    public Boolean getRegisteredAsShelter() {
        return registeredAsShelter;
    }

    public UserView setRegisteredAsShelter(Boolean registeredAsShelter) {
        this.registeredAsShelter = registeredAsShelter;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserView setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getIsBanned() {
        return isBanned;
    }

    public UserView setIsBanned(Boolean isBanned) {
        this.isBanned = isBanned;
        return this;
    }
}
