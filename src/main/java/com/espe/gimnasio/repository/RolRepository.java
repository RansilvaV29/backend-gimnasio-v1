package com.espe.gimnasio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.espe.gimnasio.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol , Integer> {

}
