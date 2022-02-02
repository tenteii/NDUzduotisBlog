package com.example.testas.service;

import com.example.testas.dto.LoginRequest;
import com.example.testas.dto.RegisterRequest;
import com.example.testas.entities.Vartotojas;
import com.example.testas.repositorijos.VartotojuRepo;
import com.example.testas.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private VartotojuRepo vartotojuRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private EmailValidacija emailValidacija;

    public void registracija(RegisterRequest registerRequest) {
        boolean isValidEmail = emailValidacija.test(registerRequest.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Netinkamas pasto adresas");
        }
        Vartotojas vartotojas = new Vartotojas();
        vartotojas.setEmail(registerRequest.getEmail());
        vartotojas.setPassword(encodePassword(registerRequest.getPassword()));
        vartotojuRepo.save(vartotojas);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String prisijungimas(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtProvider.generateToken(authenticate);
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);

    }
}
