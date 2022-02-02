package com.example.testas.repositorijos;

import com.example.testas.entities.Irasas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IrasuRepo extends JpaRepository<Irasas, Long> {
    Optional<Irasas>findByuEmail(String uEmail);
}
