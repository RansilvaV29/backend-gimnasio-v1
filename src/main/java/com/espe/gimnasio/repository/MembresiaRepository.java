package com.espe.gimnasio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.espe.gimnasio.entity.Membresia;

@Repository
public interface MembresiaRepository extends JpaRepository<Membresia, Integer>{

}
