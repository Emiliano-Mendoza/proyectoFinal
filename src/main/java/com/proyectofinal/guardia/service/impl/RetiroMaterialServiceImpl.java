package com.proyectofinal.guardia.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import com.proyectofinal.guardia.dao.AutorizacionJPARepository;
import com.proyectofinal.guardia.dao.EmpleadoJPARepository;
import com.proyectofinal.guardia.dao.MaterialJPARepository;
import com.proyectofinal.guardia.dao.NotificacionJPARepository;
import com.proyectofinal.guardia.dao.RetiroJPARepository;
import com.proyectofinal.guardia.dao.UsuarioJPARepository;
import com.proyectofinal.guardia.domain.AutorizacionRetiroMaterial;
import com.proyectofinal.guardia.domain.Empleado;
import com.proyectofinal.guardia.domain.Material;
import com.proyectofinal.guardia.domain.NotiUsuario;
import com.proyectofinal.guardia.domain.Notificacion;
import com.proyectofinal.guardia.domain.Retiro;
import com.proyectofinal.guardia.domain.Rol;
import com.proyectofinal.guardia.domain.Usuario;
import com.proyectofinal.guardia.service.RetiroMaterialService;

@Service
public class RetiroMaterialServiceImpl implements RetiroMaterialService {

	@Autowired
	private RetiroJPARepository retiroRepo;

	@Autowired
	private AutorizacionJPARepository autRepo;

	@Autowired
	private EmpleadoJPARepository empleadoRepo;

	@Autowired
	private MaterialJPARepository materialRepo;

	@Autowired
	private UsuarioJPARepository usuarioRepo;

	@Autowired
	private NotificacionJPARepository notiRepo;

	@Override
	public AutorizacionRetiroMaterial crearAutorizacion(int nroLegajo, List<Integer> materiales, String fechaLimite,
			String descripcion) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


		AutorizacionRetiroMaterial autorizacion = new AutorizacionRetiroMaterial();

		if (nroLegajo > 0) {
			autorizacion.setEmpleado(empleadoRepo.findById(nroLegajo).orElseThrow());
		} else {
			autorizacion.setEmpleado(null);
		}

		materiales.forEach((mat) -> {
			autorizacion.getMateriales().add(materialRepo.findById(mat).get());
		});

		autorizacion.setDescripcion(descripcion);

		Date fecha = formatter.parse(fechaLimite + " 23:59:59");
		autorizacion.setFechaLimite(fecha);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		autorizacion.setUsuario(usuarioRepo.findByUsername(auth.getName()));
		
		
		crearNotificaciones(autorizacion.getUsuario());
								
		
		return autRepo.save(autorizacion);
			

	}

	@Override
	public Retiro registrarRetiro(int idAutorizacion, Date fechaRetiro, String observacion, String planta) {

		Retiro retiro = new Retiro();
		retiro.setFechaRetiro(fechaRetiro);
		retiro.setObservacion(observacion);
		retiro.setPlanta(planta);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		retiro.setUsuario(usuarioRepo.findByUsername(auth.getName()));

		AutorizacionRetiroMaterial autorizacion = autRepo.findById(idAutorizacion).orElseThrow();
		autorizacion.setRetiro(retiro);

		return autRepo.save(autorizacion).getRetiro();
	}

	@Override
	public List<AutorizacionRetiroMaterial> obtenerAutorizacionesActivas() {

		return autRepo.findAllByOrderByFechaLimiteAsc().stream()
				.filter(a -> a.getFechaLimite().after(new Date()) && a.getRetiro() == null)
				.collect(Collectors.toList());
	}

	@Override
	public List<AutorizacionRetiroMaterial> obtenerAutorizaciones() {
		// TODO Auto-generated method stub
		return autRepo.findAllByOrderByFechaLimiteAsc();
	}
	
	public Boolean crearNotificaciones(Usuario usuarioAutorizador) {
		
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
				
				if(us.getIdUsuario() != usuarioAutorizador.getIdUsuario()) {
																			
					notisUsuarios.add(new NotiUsuario(us,false));
										
				}				
				
			});
			NotiUsuario[] notisArreglo = new NotiUsuario[notisUsuarios.size()];
			notisUsuarios.toArray(notisArreglo);
						
			notiRepo.save(new Notificacion(usuarioAutorizador.nombreCompleto() + " ha creado una nueva autorización de retiro."
					,"Autorización de retiro", usuarioAutorizador, new Date(), notisArreglo));
			
		}catch(Exception e) {
			return false;
		}
								
		return true;
	}

	@Override
	public List<AutorizacionRetiroMaterial> filtrarAutorizaciones(String fechaLimiteInicio, String fechaLimiteFin,
			String fechaRetiroInicio, String fechaRetiroFin, int nroLegajo, int idAutorizante, int idGuardia,
			int idMateria) throws ParseException {
				

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaLimiteInicioAux = formatter.parse(fechaLimiteInicio != null ? fechaLimiteInicio : "01/01/2000");
		Date fechaLimiteFinalAux = fechaLimiteFin != null ? new Date(formatter.parse(fechaLimiteFin).getTime() + (1000 * 60 * 60 * 24)) : new Date(3000,0,1);
		
		Date fechaRetiroInicioAux = formatter.parse(fechaRetiroInicio != null ? fechaRetiroInicio : "01/01/2000");
		Date fechaRetiroFinAux = fechaRetiroFin != null ? new Date(formatter.parse(fechaRetiroFin).getTime() + (1000 * 60 * 60 * 24)) : new Date(3000,0,1);
		
		Material material;
		if(idMateria > 0) material = materialRepo.findById(idMateria).get();
		else material = new Material();
		
		return autRepo.findAll().stream().filter(a -> 
				   (a.getFechaLimite() != null ?  a.getFechaLimite().after(fechaLimiteInicioAux) : true)
				&& (a.getFechaLimite() != null ? a.getFechaLimite().before(fechaLimiteFinalAux) : true)
				&& ((a.getRetiro() != null && a.getRetiro().getFechaRetiro() != null) ? a.getRetiro().getFechaRetiro().after(fechaRetiroInicioAux) : (fechaRetiroInicio != null ? false : true))
				&& ((a.getRetiro() != null && a.getRetiro().getFechaRetiro() != null) ? a.getRetiro().getFechaRetiro().before(fechaRetiroFinAux) : (fechaRetiroFin != null ? false : true))
				&& (idAutorizante > 0
						? (a.getUsuario() != null && a.getUsuario().getIdUsuario() == idAutorizante)									
						: true)
				&& (idGuardia > 0
						? (a.getRetiro() != null && a.getRetiro().getUsuario() != null && a.getRetiro().getUsuario().getIdUsuario() == idGuardia)									
						: true)
				&& (idMateria > 0
						? ((a.getMateriales() != null && a.getMateriales().contains(material)))
						: true)
				&& (nroLegajo > 0 ? (a.getEmpleado() != null && a.getEmpleado().getNroLegajo() == nroLegajo)
						: true))
				.collect(Collectors.toList());


	}
}
