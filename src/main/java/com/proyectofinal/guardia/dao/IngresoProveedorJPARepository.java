package com.proyectofinal.guardia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyectofinal.guardia.domain.IngresoProveedor;

public interface IngresoProveedorJPARepository extends JpaRepository<IngresoProveedor, Integer> {
	
	@Query(
			value = "SELECT * FROM ingreso_proveedor i WHERE i.egreso IS NULL", 
			nativeQuery = true)
	public List<IngresoProveedor> findAllActivos();
	
}
