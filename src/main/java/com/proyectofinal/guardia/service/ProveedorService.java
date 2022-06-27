package com.proyectofinal.guardia.service;

import java.util.List;

import com.proyectofinal.guardia.domain.Proveedor;

public interface ProveedorService {

	public Boolean validarDatos();
	public Proveedor crearProveedor(String nombreProveedor, String descripcion, Boolean activo);
	public Proveedor crearProveedor(Proveedor proveedor);
	public Proveedor editarProveedor(Proveedor proveedor);
	public Proveedor editarProveedor(int idProveedor, String nombreProveedor, String descripcion, Boolean activo);
	public List<Proveedor> obtenerTodos();
	public List<Proveedor> obtenerDisponibles();
	
}
