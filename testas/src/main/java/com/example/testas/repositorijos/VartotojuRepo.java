package com.example.testas.repositorijos;

import com.example.testas.entities.Vartotojas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VartotojuRepo extends JpaRepository<Vartotojas, Long> {
    Optional<Vartotojas>findByEmail(String email);
}
