package com.espe.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espe.gimnasio.entity.Ejercicio;
import com.espe.gimnasio.entity.EjercicioMusculo;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer>{

}
