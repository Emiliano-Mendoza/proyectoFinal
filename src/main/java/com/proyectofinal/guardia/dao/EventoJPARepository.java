package com.proyectofinal.guardia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.guardia.domain.Evento;

public interface EventoJPARepository extends JpaRepository<Evento, Integer> {
	
	public List<Evento> findAllByOrderByFechaEventoAsc();
	
}
