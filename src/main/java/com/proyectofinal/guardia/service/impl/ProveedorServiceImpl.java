package com.proyectofinal.guardia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.ProveedorJPARepository;
import com.proyectofinal.guardia.domain.Proveedor;
import com.proyectofinal.guardia.service.ProveedorService;

@Service
public class ProveedorServiceImpl implements ProveedorService {
	
	@Autowired
	private ProveedorJPARepository proveedorRepo;
	
	@Override
	public Boolean validarDatos() {

		return true;
	}
	
	@Override
	public Proveedor crearProveedor(Proveedor proveedor) {
		
		return proveedorRepo.save(proveedor);
	}

	@Override
	public Proveedor editarProveedor(Proveedor proveedor) {
		
		return proveedorRepo.save(proveedor);
	}
	
	
	@Override
	public Proveedor crearProveedor(String nombreProveedor, String descripcion, Boolean activo) {

		Proveedor proveedor = new Proveedor();
		proveedor.setProveedor(nombreProveedor);
		proveedor.setDescripcion(descripcion);
		proveedor.setActivo(activo);
		
		return proveedorRepo.save(proveedor);
	}

	@Override
	public Proveedor editarProveedor(int idProveedor, String nombreProveedor, String descripcion, Boolean activo) {
		
		Proveedor proveedor = proveedorRepo.getById(idProveedor);
		
		proveedor.setProveedor(nombreProveedor);
		proveedor.setDescripcion(descripcion);
		proveedor.setActivo(activo);
		
		return proveedorRepo.save(proveedor);
	}

	@Override
	public List<Proveedor> obtenerTodos() {
		
		return proveedorRepo.findAll();
	}

	@Override
	public List<Proveedor> obtenerDisponibles() {
		
		return proveedorRepo.findAllDisponibles();
	}



}
