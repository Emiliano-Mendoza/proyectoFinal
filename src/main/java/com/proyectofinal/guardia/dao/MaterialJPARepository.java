package com.proyectofinal.guardia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.guardia.domain.Material;

public interface MaterialJPARepository extends JpaRepository<Material, Integer> {

}
