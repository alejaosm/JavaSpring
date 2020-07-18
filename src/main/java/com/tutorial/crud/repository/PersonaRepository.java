package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Integer> {

    Optional<Persona> findByNombre(String nombre);
    boolean existsByNombre (String nombre);
}
