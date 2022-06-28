package com.proyectofinal.guardia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.EmpleadoJPARepository;
import com.proyectofinal.guardia.domain.Empleado;
import com.proyectofinal.guardia.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoJPARepository empleadoRepo;

	@Override
	public Boolean validarDatos(Empleado empleado) {

		return empleadoRepo.existsById(empleado.getNroLegajo());

	}

	@Override
	public Empleado crearEmpleado(Empleado empleado) {

		
		empleado.setEnPlanta(false);
		
		return empleadoRepo.save(empleado);
	}

	@Override
	public List<Empleado> obtenerTodos() {

		return empleadoRepo.findAll();
	}

	@Override
	public List<Empleado> obtenerDisponibles() {

		return empleadoRepo.findAllDisponibles();
	}

	@Override
	public Void marcarEmpleadoEnPlanta(int nroLegajo) {

		Empleado empleado = empleadoRepo.findById(nroLegajo).orElseThrow();

		empleado.setEnPlanta(true);
		empleadoRepo.save(empleado);

		return null;
	}

	@Override
	public Void marcarEmpleadoEgresadoDePlanta(int nroLegajo) {
		Empleado empleado = empleadoRepo.findById(nroLegajo).orElseThrow();

		empleado.setEnPlanta(false);
		empleadoRepo.save(empleado);

		return null;
	}

	@Override
	public Empleado editarEmpleado(Empleado empleado) {

		Empleado empActual = empleadoRepo.findById(empleado.getNroLegajo()).orElseThrow();

		if (empleado.getImagen() == null) {

			empleado.setImagen(empActual.getImagen());

		}
		empleado.setEnPlanta(empActual.getEnPlanta());

		return empleadoRepo.save(empleado);
	}

	@Override
	public Optional<Empleado> buscarEmpleado(int nroLegajo) {

		return empleadoRepo.findById(nroLegajo);
	}

}
