package com.espe.gimnasio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espe.gimnasio.entity.Musculo;

public interface MusculoRepository extends JpaRepository<Musculo, Integer> {
    Optional<Musculo> findByNombreIgnoreCase(String nombre);

}
