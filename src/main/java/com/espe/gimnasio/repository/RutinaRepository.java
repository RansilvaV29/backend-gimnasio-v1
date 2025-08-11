package com.espe.gimnasio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.espe.gimnasio.entity.Rutina;

public interface RutinaRepository extends JpaRepository<Rutina, Integer>{
	@Query("""
		    SELECT DISTINCT r FROM Rutina r
		    JOIN r.rutinaEjercicios re
		    JOIN re.ejercicio e
		    JOIN e.ejercicioMusculos em
		    WHERE em.musculo.id = :musculoId
		""")
		List<Rutina> findByMusculoId(@Param("musculoId") Integer musculoId);
}
