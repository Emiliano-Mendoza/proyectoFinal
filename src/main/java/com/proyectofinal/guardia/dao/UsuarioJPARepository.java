package com.proyectofinal.guardia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.guardia.domain.Usuario;

public interface UsuarioJPARepository extends JpaRepository<Usuario, Integer> {

}
