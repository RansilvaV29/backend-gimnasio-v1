package com.espe.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.espe.gimnasio.entity.Historialcorporal;

public interface HistorialCorporalRepository extends JpaRepository<Historialcorporal, Integer> {
    List<Historialcorporal> findByUsuario_IdUsuario(Integer idUsuario);

}