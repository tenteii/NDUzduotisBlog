package com.example.testas.controllers;

import com.example.testas.dto.LoginRequest;
import com.example.testas.dto.RegisterRequest;
import com.example.testas.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/registracija")
    public ResponseEntity registracija(@RequestBody RegisterRequest registerRequest){
        authService.registracija(registerRequest);
        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping("/prisijungimas")
    public String prisijungimas(@RequestBody LoginRequest loginRequest) {
        return authService.prisijungimas(loginRequest);
    }
}
