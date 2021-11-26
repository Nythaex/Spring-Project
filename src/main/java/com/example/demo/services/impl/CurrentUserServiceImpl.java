package com.example.demo.services.impl;

import com.example.demo.models.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserRoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrentUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    public CurrentUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User byEmail = userRepository.findByEmail(email);

        if (byEmail==null){
            throw new UsernameNotFoundException("User with email "+email+" not found!");
        }

        return mapUser(byEmail);
    }

    public UserDetails mapUser(User user){
        List<GrantedAuthority> grantedAuthorities=user.getRoles().stream().map(r->new SimpleGrantedAuthority("ROLE_"+r.getRole().name())).collect(Collectors.toList());

        return new CurrentUser(
                user.getUsername(),
                user.getPassword(),
                !user.getBanned(),
                grantedAuthorities,
                user.getId());
    }
}
