package com.proyectofinal.guardia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.guardia.domain.AutorizacionRetiroMaterial;

public interface AutorizacionJPARepository extends JpaRepository<AutorizacionRetiroMaterial, Integer> {
	
	public List<AutorizacionRetiroMaterial> findAllByOrderByFechaLimiteAsc();
	
}
