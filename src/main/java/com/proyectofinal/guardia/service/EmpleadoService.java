package com.proyectofinal.guardia.service;

import java.util.List;

import com.proyectofinal.guardia.domain.Empleado;

public interface EmpleadoService {
	
	public Boolean validarDatos(Empleado empleado);
	public Empleado crearEmpleado(Empleado empleado, int idSector);
	public Empleado editarEmpleado(Empleado empleado, int idSector);
	public List<Empleado> obtenerTodos();
	public List<Empleado> obtenerDisponibles();
	public Void marcarEmpleadoEnPlanta();
	public Void marcarEmpleadoEgresadoDePlanta();
	
	
}
