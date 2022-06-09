package com.proyectofinal.guardia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.EmpleadoJPARepository;
import com.proyectofinal.guardia.dao.SectorTrabajoJPARepository;
import com.proyectofinal.guardia.domain.Empleado;
import com.proyectofinal.guardia.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
	
	@Autowired
	private EmpleadoJPARepository empleadoRepo;
	
	@Autowired
	private SectorTrabajoJPARepository sectorRepo;
	
	@Override
	public Boolean validarDatos(int nroLegajo, String nombre, String apellido, int idSector, String imagen) {
		
		return true;
	}

	@Override
	public Empleado crearEmpleado(int nroLegajo, String nombre, String apellido, int idSector, String imagen, Boolean activo) {

		Empleado empleado = new Empleado();
		empleado.setActivo(activo);
		empleado.setApellido(apellido);
		empleado.setNombre(nombre);
		empleado.setEnPlanta(false);
		empleado.setImagen(imagen);
		empleado.setSector(sectorRepo.getById(idSector));
					
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
	public Void marcarEmpleadoEnPlanta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void marcarEmpleadoEgresadoDePlanta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado editarEmpleado(int nroLegajo, String nombre, String apellido, int idSector, String imagen,
			Boolean activo) {
		// TODO Auto-generated method stub
		return null;
	}

}
