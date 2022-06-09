package com.proyectofinal.guardia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.guardia.domain.AutorizacionRetiroMaterial;

public interface AutorizacionJPARepository extends JpaRepository<AutorizacionRetiroMaterial, Integer> {

}
