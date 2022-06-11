package com.proyectofinal.guardia.service;

import java.util.List;

import com.proyectofinal.guardia.domain.Empleado;

public interface EmpleadoService {
	
	public Boolean validarDatos(int nroLegajo);
	public Empleado crearEmpleado(Empleado empleado, int idSector);
	public Empleado editarEmpleado(int nroLegajo, String nombre, String apellido, int idSector, String imagen, Boolean activo);
	public List<Empleado> obtenerTodos();
	public List<Empleado> obtenerDisponibles();
	public Void marcarEmpleadoEnPlanta();
	public Void marcarEmpleadoEgresadoDePlanta();
	
	
}
