package com.proyectofinal.guardia.service;

import java.util.Date;
import java.util.List;

import com.proyectofinal.guardia.domain.IngresoProveedor;

public interface IngresoProveedorService {
	
	public IngresoProveedor crearIngresoProveedor(int idProveedor, Date ingreso, String planta, String chofer, String patente);
	public IngresoProveedor egresoProveedor(int idIngreso, Date egreso);
	public List<IngresoProveedor> obtenerIngresos();
	public List<IngresoProveedor> obtenerIngresosActivos();
	public List<IngresoProveedor> listarIngresos();
	
}
