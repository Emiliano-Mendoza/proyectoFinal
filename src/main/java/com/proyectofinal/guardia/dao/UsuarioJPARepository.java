package com.proyectofinal.guardia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyectofinal.guardia.domain.Usuario;

public interface UsuarioJPARepository extends JpaRepository<Usuario, Integer> {
	
	Usuario findByUsername(String us);
	
	@Query(
			value = "SELECT * FROM usuario u WHERE u.activo = true", 
			nativeQuery = true)
	public List<Usuario> findAllDisponibles();
}
