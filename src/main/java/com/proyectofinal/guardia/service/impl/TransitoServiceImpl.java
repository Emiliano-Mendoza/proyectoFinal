package com.proyectofinal.guardia.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.AsistenciaJPARepository;
import com.proyectofinal.guardia.dao.TipoTransitoJPARepository;
import com.proyectofinal.guardia.dao.TransitoJPARepository;
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

	@Override
	public Transito crearTransito(int idAsistencia, Date salida, Vehiculo vehiculo) {

		Transito transito = new Transito();
		Asistencia asis = asisRepo.getById(idAsistencia);

		transito.setAsistencia(asis);
		transito.setEgreso(salida);
		transito.setEmpleado(asis.getEmpleado());
		transito.setTipo(tipoRepo.getById(1));
		transito.setPrimerVehiculo(vehiculo);

		return transitoRepo.save(transito);
	}

	@Override
	public Transito reingresoTransito(int idTransito, Date reingreso, Vehiculo vehiculo) {

		Transito transito = transitoRepo.getById(idTransito);
		transito.setIngreso(reingreso);
		transito.setSegundoVehiculo(vehiculo);

		return transitoRepo.save(transito);

	}

	@Override
	public List<Transito> obtenerTransitos() {

		return transitoRepo.findAllByOrderByEgresoAsc();
	}

	@Override
	public List<Transito> obtenerTransitosActivos() {

		return transitoRepo.findAllByOrderByEgresoAsc().stream().filter(t -> (t.getIngreso() == null && t.getTipo().getIdTipo() == 1))
				.collect(Collectors.toList());
	}

}
