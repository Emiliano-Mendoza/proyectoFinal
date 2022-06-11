package com.proyectofinal.guardia.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.IngresoProveedorJPARepository;
import com.proyectofinal.guardia.dao.ProveedorJPARepository;
import com.proyectofinal.guardia.domain.IngresoProveedor;
import com.proyectofinal.guardia.service.IngresoProveedorService;

@Service
public class IngresoProveedorServiceImpl implements IngresoProveedorService {
	
	@Autowired
	private IngresoProveedorJPARepository ingresoRepo;
	
	@Autowired
	private ProveedorJPARepository proveedorRepo;
	
	@Override
	public IngresoProveedor crearIngresoProveedor(int idProveedor, Date ingreso, String planta, String chofer,
			String patente) {
		
		IngresoProveedor ingresoProveedor = new IngresoProveedor();
		ingresoProveedor.setProveedor(proveedorRepo.getById(idProveedor));
		ingresoProveedor.setIngreso(ingreso);
		ingresoProveedor.setPlanta(planta);
		ingresoProveedor.setNombreChofer(chofer);
		ingresoProveedor.setPatenteVehiculo(patente);
						
		return ingresoRepo.save(ingresoProveedor);
	}

	@Override
	public IngresoProveedor egresoProveedor(int idIngreso, Date egreso) {
		
		IngresoProveedor ingresoProveedor = ingresoRepo.getById(idIngreso);
		ingresoProveedor.setEgreso(egreso);
		
		return ingresoRepo.save(ingresoProveedor);
	}

	@Override
	public List<IngresoProveedor> obtenerIngresos() {
		
		return ingresoRepo.findAll();
	}

	@Override
	public List<IngresoProveedor> obtenerIngresosActivos() {
		
		return ingresoRepo.findAllActivos();
	}

}