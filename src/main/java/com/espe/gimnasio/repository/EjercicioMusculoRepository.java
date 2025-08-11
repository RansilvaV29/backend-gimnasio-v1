package com.espe.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espe.gimnasio.entity.EjercicioMusculo;

public interface EjercicioMusculoRepository extends JpaRepository<EjercicioMusculo, Integer>{
    List<EjercicioMusculo> findByEjercicioId(Integer ejercicioId);
}
