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
import com.proyectofinal.guardia.dao.UsuarioJPARepository;
import com.proyectofinal.guardia.domain.Asistencia;
import com.proyectofinal.guardia.domain.Empleado;
import com.proyectofinal.guardia.service.AsistenciaService;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

	@Autowired
	private AsistenciaJPARepository asisRepo;

	@Autowired
	private UsuarioJPARepository usuarioRepo;

	@Override
	public Asistencia crearAsistencia(Empleado empleado, Date ingreso, String planta) {

		Asistencia asistencia = new Asistencia();
		asistencia.setIngreso(ingreso);
		asistencia.setEmpleado(empleado);
		asistencia.setPlanta(planta);
		asistencia.setEnTransito(false);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		asistencia.setUsuarioIngreso(usuarioRepo.findByUsername(auth.getName()));

		return asisRepo.save(asistencia);
	}

	@Override
	public Asistencia egresoAsistencia(int idAsistencia, Date egreso) {

		Asistencia asistencia = asisRepo.getById(idAsistencia);
		asistencia.setEgreso(egreso);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		asistencia.setUsuarioEgreso(usuarioRepo.findByUsername(auth.getName()));

		return asisRepo.save(asistencia);
	}

	@Override
	public List<Asistencia> listarAsistenciasSinEgresoSinTransito() {

		return asisRepo.findAllByOrderByIngresoAsc().stream().filter(a -> a.getEgreso() == null && !a.getEnTransito())
				.collect(Collectors.toList());
	}

	@Override
	public List<Asistencia> listarAsistenciasSinEgreso() {

		return asisRepo.findAllByOrderByIngresoAsc().stream().filter(a -> a.getEgreso() == null)
				.collect(Collectors.toList());
	}

	@Override
	public void marcarAsistenciaEnTransito(int idAsistencia) {

		Asistencia asistencia = asisRepo.getById(idAsistencia);
		asistencia.setEnTransito(true);

		asisRepo.save(asistencia);

	}

	@Override
	public void removerMarcarEnTransito(int idAsistencia) {
		Asistencia asistencia = asisRepo.getById(idAsistencia);
		asistencia.setEnTransito(false);

		asisRepo.save(asistencia);

	}

	@Override
	public List<Asistencia> listarAsistencias() {

		return asisRepo.findAllByOrderByIngresoAsc();
	}

	@Override
	public List<Asistencia> filtrarAsistencias(String fechaInicio, String fechaFin, int nroLegajo, int idUsuario) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaInicioAux = formatter.parse(fechaInicio != null ? fechaInicio : "01/01/2000");
		Date fechaFinalAux = fechaFin != null ? new Date(formatter.parse(fechaFin).getTime() + (1000 * 60 * 60 * 24)) : new Date(3000,0,1);
		
		return asisRepo.findAll().stream().filter(a -> 
				   (a.getIngreso() != null ?  a.getIngreso().after(fechaInicioAux) : true)
				&& (a.getIngreso() != null ? a.getIngreso().before(fechaFinalAux) : true)
				&& (idUsuario > 0
						? ((a.getUsuarioIngreso() != null && a.getUsuarioIngreso().getIdUsuario() == idUsuario)
								|| (a.getUsuarioEgreso() != null
										&& a.getUsuarioEgreso().getIdUsuario() == idUsuario))
						: true)
				&& (nroLegajo > 0 ? (a.getEmpleado() != null && a.getEmpleado().getNroLegajo() == nroLegajo)
						: true))
				.collect(Collectors.toList());
	}

}
