package com.example.demo.services.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private Boolean enabled;
    private Long id;

    public CurrentUser(String username, String password, Boolean enabled,Collection<? extends GrantedAuthority> authorities,Long id) {
        super(username, password, authorities);
        this.enabled=enabled;
        this.id=id;

    }

    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }


    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public Long getId() {
        return id;
    }
}
