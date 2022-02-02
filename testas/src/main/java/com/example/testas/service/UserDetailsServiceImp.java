package com.example.testas.service;

import com.example.testas.entities.Vartotojas;
import com.example.testas.repositorijos.VartotojuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private VartotojuRepo vartotojuRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Vartotojas vartotojas = vartotojuRepo.findByEmail(username).orElseThrow(()->
                new UsernameNotFoundException("Vartotojas su tokiu email nerastas" + username));
        return new org.springframework.security.core.userdetails.User(vartotojas.getEmail(),
                vartotojas.getPassword(),
                true, true, true, true,
                getAuthorities("ROLE_USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
    }
}
