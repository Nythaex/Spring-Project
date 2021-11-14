package com.example.demo.models.entities;

import com.example.demo.models.enums.Role;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "role")
public class UserRole extends BasicEntity{

    private Role role;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public UserRole() {

    }

    public UserRole(Role role) {
        this.role = role;

    }

    public Role getRole() {
        return role;
    }

    public UserRole setRole(Role role) {
        this.role = role;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public UserRole setUsers(List<User> users) {
        this.users = users;
        return this;
    }
}
