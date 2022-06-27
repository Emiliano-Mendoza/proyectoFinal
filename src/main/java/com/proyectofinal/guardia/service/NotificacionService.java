package com.proyectofinal.guardia.service;

import java.util.List;

import com.proyectofinal.guardia.helpers.NotificacionHelper;

public interface NotificacionService {
	
	public List<NotificacionHelper> obtenerNotificaciones();
	public void marcarNotificacionesLeidas(List<Integer> listaIds);
	
}
