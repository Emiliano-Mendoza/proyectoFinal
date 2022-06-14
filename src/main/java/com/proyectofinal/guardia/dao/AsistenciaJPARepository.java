package com.proyectofinal.guardia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.guardia.domain.Asistencia;

public interface AsistenciaJPARepository extends JpaRepository<Asistencia, Integer> {
	
	public List<Asistencia> findAllByOrderByIngresoAsc();
	
}
