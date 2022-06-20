package com.proyectofinal.guardia.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.NotiUsuarioJPARepository;
import com.proyectofinal.guardia.dao.NotificacionJPARepository;
import com.proyectofinal.guardia.dao.UsuarioJPARepository;
import com.proyectofinal.guardia.domain.NotiUsuario;
import com.proyectofinal.guardia.domain.Notificacion;
import com.proyectofinal.guardia.domain.Usuario;
import com.proyectofinal.guardia.helpers.NotificacionHelper;
import com.proyectofinal.guardia.service.NotificacionService;

@Service
public class NotificacionServiceImpl implements NotificacionService {

	@Autowired
	private NotificacionJPARepository notiRepo;

	@Autowired
	private UsuarioJPARepository usuarioRepo;

	@Autowired
	private NotiUsuarioJPARepository notiUsRepo;

	@Override
	public List<NotificacionHelper> obtenerNotificaciones() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioRepo.findByUsername(auth.getName());

		List<NotiUsuario> listaNotiUs = notiUsRepo.findAll().stream()
				.filter(n -> !n.getLeido() && n.getUsuarioNotificado().getIdUsuario() == usuario.getIdUsuario())
				.collect(Collectors.toList());
		
		List<NotificacionHelper> listaNotis = new ArrayList<>();
		
		listaNotiUs.forEach((nu) -> {
			
			NotificacionHelper notiHelper = new NotificacionHelper();
			notiHelper.setIdNotiUsuario(nu.getId());
			notiHelper.setLeido(nu.getLeido());
			notiHelper.setMensaje(nu.getNotificacion().getDescripcion());
			notiHelper.setFecha(nu.getNotificacion().getFecha());
			
			listaNotis.add(notiHelper);
			
		});
		

		
		return listaNotis;
	}
	/*
	 * @Override public List<Notificacion> obtenerNotificaciones() {
	 * 
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * Usuario usuario = usuarioRepo.findByUsername(auth.getName());
	 * 
	 * List<Notificacion> listaNotificaciones = notiRepo.findAll();
	 * 
	 * return listaNotificaciones; }
	 */

	@Override
	public void marcarNotificacionesLeidas(List<Integer> listaIds) {
		
		List<NotiUsuario> listaNotiUs = notiUsRepo.findAll();
		List<NotiUsuario> listaAux = new ArrayList<>();
		
		listaNotiUs.forEach((nu)->{
			
			if(listaIds.contains(nu.getId())) {
				nu.setLeido(true);
				listaAux.add(nu);
			}
			
		});
		
		notiUsRepo.saveAll(listaAux);
		
	}
	
	public Boolean FiltrarNotis() {
		
		return true;
	}
}
