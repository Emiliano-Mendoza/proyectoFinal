package com.proyectofinal.guardia.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.proyectofinal.guardia.domain.Empleado;

@DataJpaTest
public class EmpleadoJPARepositoryTest {
	@Autowired
	EmpleadoJPARepository empleadoRepo;
	
	@Test
	public void findAllDisponiblesShouldReturn1() {
		Empleado empleado = new Empleado();
		empleado.setNroLegajo(100);
		empleado.setNombre("empleado test");
		empleado.setApellido("apellido test");
		empleado.setActivo(true);
		empleado.setEnPlanta(true);
		
		var savedEmpleado = empleadoRepo.save(empleado);
                 
		var resultado = empleadoRepo.findAllDisponibles();
		
		assertEquals(1, resultado.size());
	}
}
