package com.proyectofinal.guardia.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.AsistenciaJPARepository;
import com.proyectofinal.guardia.dao.TipoTransitoJPARepository;
import com.proyectofinal.guardia.dao.TransitoJPARepository;
import com.proyectofinal.guardia.dao.UsuarioJPARepository;
import com.proyectofinal.guardia.domain.Asistencia;
import com.proyectofinal.guardia.domain.Transito;
import com.proyectofinal.guardia.domain.Vehiculo;
import com.proyectofinal.guardia.service.TransitoService;

@Service
public class TransitoServiceImpl implements TransitoService {

	@Autowired
	private TransitoJPARepository transitoRepo;

	@Autowired
	private TipoTransitoJPARepository tipoRepo;

	@Autowired
	private AsistenciaJPARepository asisRepo;

	@Autowired
	private UsuarioJPARepository usuarioRepo;

	@Override
	public Transito crearTransito(int idAsistencia, Date salida, Vehiculo vehiculo, String comentario) {

		Transito transito = new Transito();
		Asistencia asis = asisRepo.getById(idAsistencia);

		transito.setAsistencia(asis);
		transito.setEgreso(salida);
		transito.setEmpleado(asis.getEmpleado());
		transito.setTipo(tipoRepo.getById(1));
		transito.setPrimerVehiculo(vehiculo);
		transito.setPrimerComentario(comentario);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		transito.setUsuarioEgreso(usuarioRepo.findByUsername(auth.getName()));

		return transitoRepo.save(transito);
	}

	@Override
	public Transito reingresoTransito(int idTransito, Date reingreso, Vehiculo vehiculo, String comentario) {

		Transito transito = transitoRepo.getById(idTransito);
		transito.setIngreso(reingreso);
		transito.setSegundoVehiculo(vehiculo);
		transito.setSegundoComentario(comentario);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		transito.setUsuarioIngreso(usuarioRepo.findByUsername(auth.getName()));

		return transitoRepo.save(transito);

	}

	@Override
	public List<Transito> obtenerTransitos() {

		return transitoRepo.findAllByOrderByEgresoAsc();
	}

	@Override
	public List<Transito> obtenerTransitosActivos() {

		return transitoRepo.findAllByOrderByEgresoAsc().stream()
				.filter(t -> (t.getIngreso() == null && t.getTipo().getIdTipo() == 1)).collect(Collectors.toList());
	}

	@Override
	public List<Transito> filtrarTransitos(String fechaInicio, String fechaFin, int nroLegajo, int idUsuario,
			int idVehiculo) {

		try {
						
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaInicioAux = formatter.parse(fechaInicio != null ? fechaInicio : "01/01/2000");
			Date fechaFinalAux =  fechaInicio != null ? new Date(formatter.parse(fechaFin).getTime() + (1000 * 60 * 60 * 24)) : new Date(3000,0,1);

			return transitoRepo.findAll().stream()
					.filter(t -> (t.getEgreso() != null ? t.getEgreso().after(fechaInicioAux) : true)
							&& (t.getEgreso() != null ?  t.getEgreso().before(fechaFinalAux) : true)
							&& (idUsuario > 0
									? ((t.getUsuarioIngreso() != null && t.getUsuarioIngreso().getIdUsuario() == idUsuario)
											|| (t.getUsuarioEgreso() != null
													&& t.getUsuarioEgreso().getIdUsuario() == idUsuario))
									: true)
							&& (nroLegajo > 0 ? (t.getEmpleado() != null && t.getEmpleado().getNroLegajo() == nroLegajo)
									: true)
							&& (idVehiculo > 0 ? ((t.getPrimerVehiculo() != null && t.getPrimerVehiculo().getIdVehiculo() == idVehiculo) ||
									(t.getSegundoVehiculo() != null && t.getSegundoVehiculo().getIdVehiculo() == idVehiculo))
									: true))
					.collect(Collectors.toList());

		} catch (ParseException e) {

			e.printStackTrace();
		}

		return null;
	}

}
