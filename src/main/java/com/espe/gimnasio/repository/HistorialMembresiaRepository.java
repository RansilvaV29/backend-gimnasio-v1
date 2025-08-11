package com.espe.gimnasio.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.espe.gimnasio.entity.Historialmambresia;

@Repository
public interface HistorialMembresiaRepository extends JpaRepository<Historialmambresia, Integer>{
	List<Historialmambresia> findByEstadoTrueAndFechaFinBefore(Date fecha);
	
	@Query("SELECT h FROM Historialmambresia h WHERE h.usuario.idUsuario = :idUsuario AND h.estado = true")
	Optional<Historialmambresia> findMembresiaActivaByUsuario(@Param("idUsuario") Integer idUsuario);

}