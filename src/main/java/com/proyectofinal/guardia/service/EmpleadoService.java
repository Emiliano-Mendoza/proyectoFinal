package com.proyectofinal.guardia.service;

import java.util.List;
import java.util.Optional;

import com.proyectofinal.guardia.domain.Empleado;

public interface EmpleadoService {
	
	public Boolean validarDatos(Empleado empleado);
	public Empleado crearEmpleado(Empleado empleado);
	public Empleado editarEmpleado(Empleado empleado);
	public List<Empleado> obtenerTodos();
	public List<Empleado> obtenerDisponibles();
	public Void marcarEmpleadoEnPlanta(int nroLegajo);
	public Void marcarEmpleadoEgresadoDePlanta(int nroLegajo);
	public Optional<Empleado> buscarEmpleado(int nroLegajo);
	
}
