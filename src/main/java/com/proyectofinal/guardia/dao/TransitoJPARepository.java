package com.proyectofinal.guardia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.guardia.domain.Transito;

public interface TransitoJPARepository extends JpaRepository<Transito, Integer> {
	
	public List<Transito> findAllByOrderByEgresoAsc();
	
}
