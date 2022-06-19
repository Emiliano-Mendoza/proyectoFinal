package com.proyectofinal.guardia.service;

import java.util.List;

import com.proyectofinal.guardia.domain.NotiUsuario;
import com.proyectofinal.guardia.domain.Notificacion;
import com.proyectofinal.guardia.helpers.NotificacionHelper;

public interface NotificacionService {
	
	//public List<NotiUsuario> obtenerNotificaciones();
	public List<NotificacionHelper> obtenerNotificaciones();
	public void marcarNotificacionesLeidas(List<Integer> listaIds);
	
}
