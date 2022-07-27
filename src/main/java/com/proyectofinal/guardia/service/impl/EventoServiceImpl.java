package com.proyectofinal.guardia.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import com.proyectofinal.guardia.dao.EventoJPARepository;
import com.proyectofinal.guardia.dao.NotificacionJPARepository;
import com.proyectofinal.guardia.dao.UsuarioJPARepository;
import com.proyectofinal.guardia.domain.Evento;
import com.proyectofinal.guardia.domain.NotiUsuario;
import com.proyectofinal.guardia.domain.Notificacion;
import com.proyectofinal.guardia.domain.Rol;
import com.proyectofinal.guardia.domain.Usuario;
import com.proyectofinal.guardia.service.EventoService;

@Service
public class EventoServiceImpl implements EventoService {
	
	@Autowired
	private EventoJPARepository eventoRepo;
	@Autowired
	private UsuarioJPARepository usuarioRepo;
	@Autowired
	private NotificacionJPARepository notiRepo;
	
	@Override
	public Evento crearEvento(Evento evento, String fecha) throws ParseException {
						
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date fechaInicioAux = formatter.parse(fecha);
		
		evento.setCancelado(false);
		evento.setFechaEvento(fechaInicioAux);
		evento.setOcurrencia(false);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		evento.setNotificador(usuarioRepo.findByUsername(auth.getName()));
				
		crearNotificaciones(evento.getNotificador());
		
		return eventoRepo.save(evento);
	}


	@Override
	public List<Evento> obtenerTodos() {
		
		return eventoRepo.findAll();
	}

	@Override
	public List<Evento> obtenerActivos() {
		
		Date fechaAux = new Date(new Date().getTime() - 3*(1000 * 60 * 60 * 24));
		
		List<Evento> listaEvento = eventoRepo.findAllByOrderByFechaEventoAsc()
				.stream()
				.filter(e -> e.getFechaEvento() != null
						&& e.getFechaEvento().after(fechaAux)
						&& e.getOcurrencia() != null
						&& !e.getOcurrencia()
						&& e.getCancelado() != null
						&& !e.getCancelado())						
				.collect(Collectors.toList());
		
		return listaEvento;
	}

	@Override
	public List<Evento> obtenerActivosUsuario() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Date fechaAux = new Date(new Date().getTime() - 3*(1000 * 60 * 60 * 24));
				
		List<Evento> listaEvento = eventoRepo.findAllByOrderByFechaEventoAsc()
				.stream()
				.filter(e -> e.getFechaEvento() != null
						&& e.getFechaEvento().after(fechaAux) 
						&& e.getNotificador().getIdUsuario() == usuarioRepo.findByUsername(auth.getName()).getIdUsuario()
						&& e.getOcurrencia() != null
						&& !e.getOcurrencia())
				.collect(Collectors.toList());
		
		return listaEvento;
	}

	@Override
	public Optional<Evento> obtenerPorId(int idEvento) {
		
		return eventoRepo.findById(idEvento);
	}


	@Override
	public Evento editarEvento(Evento evento) {
		
		return eventoRepo.save(evento);
	}
	
		
	//notificaciones
	public Boolean crearNotificaciones(Usuario usuario) {
		
		try {
			
			List<Usuario> listaUsuarios = usuarioRepo.findAll().stream().filter(u -> {
				
				Iterator<Rol> iterator = u.getRoles().iterator();
				Rol rol = new Rol();

				while (iterator.hasNext()) {
					rol = iterator.next();
					if (rol.getRol().equals("GUARDIA") || rol.getRol().equals("ADMIN"))
						return true;

				}
				return false;
				
			}).collect(Collectors.toList());
			
			List<NotiUsuario> notisUsuarios = new ArrayList<>();
			
			listaUsuarios.forEach((us) -> {
				
				if(us.getIdUsuario() != usuario.getIdUsuario()) {
																			
					notisUsuarios.add(new NotiUsuario(us,false));
										
				}				
				
			});
			NotiUsuario[] notisArreglo = new NotiUsuario[notisUsuarios.size()];
			notisUsuarios.toArray(notisArreglo);
						
			notiRepo.save(new Notificacion(usuario.nombreCompleto() + " ha notificado un nuevo evento próximo."
					,"Autorización de retiro", usuario, new Date(), notisArreglo));
			
		}catch(Exception e) {
			return false;
		}
								
		return true;
	}
	
}
