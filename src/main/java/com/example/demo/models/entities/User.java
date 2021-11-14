package com.example.demo.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User extends BasicEntity{

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> roles;
    @Column(nullable = false)
    private Boolean isBanned;

    @Column(unique = true,nullable = false)
    private String username;


    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "from")
    private Set<Message> mine;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "to")
    private Set<Message> theirs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Town town;


    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Shelter shelter;


    public User() {
        this.roles=new HashSet<>();
        this.isBanned=false;
    }

    public User(Boolean isBanned, String username, String password, String email, Set<Message> mine,Set<Message> theirs, Town town, Shelter shelter) {
        this.isBanned = isBanned;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mine=mine;
        this.theirs=theirs;
        this.town = town;

        this.shelter=shelter;
        this.roles=new HashSet<>();
    }


    public Set<UserRole> getRoles() {
        return roles;
    }

    public User setRoles(Set<UserRole> roles) {
        this.roles = roles;
        return this;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public User setBanned(Boolean banned) {
        isBanned = banned;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<Message> getMine() {
        return mine;
    }

    public User setMine(Set<Message> mine) {
        this.mine = mine;
        return this;
    }

    public Set<Message> getTheirs() {
        return theirs;
    }

    public User setTheirs(Set<Message> theirs) {
        this.theirs = theirs;
        return this;
    }

    public Town getTown() {
        return town;
    }

    public User setTown(Town town) {
        this.town = town;
        return this;
    }


    public Shelter getShelter() {
        return shelter;
    }

    public User setShelter(Shelter shelter) {
        this.shelter = shelter;
        return this;
    }
}
