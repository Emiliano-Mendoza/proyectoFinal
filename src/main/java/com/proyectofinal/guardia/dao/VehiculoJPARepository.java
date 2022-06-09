package com.proyectofinal.guardia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.guardia.domain.Vehiculo;

public interface VehiculoJPARepository extends JpaRepository<Vehiculo, Integer> {

}
