package com.proyectofinal.guardia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.guardia.domain.Notificacion;

public interface NotificacionJPARepository extends JpaRepository<Notificacion, Integer> {
	
}
