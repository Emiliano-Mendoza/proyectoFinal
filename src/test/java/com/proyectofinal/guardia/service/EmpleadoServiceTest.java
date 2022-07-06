package com.proyectofinal.guardia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.proyectofinal.guardia.dao.EmpleadoJPARepository;
import com.proyectofinal.guardia.dao.SectorTrabajoJPARepository;
import com.proyectofinal.guardia.domain.Empleado;
import com.proyectofinal.guardia.domain.SectorTrabajo;

@SpringBootTest
public class EmpleadoServiceTest {
	@Autowired
	private EmpleadoService empleadoSrv;

	@MockBean
	private EmpleadoJPARepository empleadoRepo;
	
	@MockBean
	private SectorTrabajoJPARepository sectorRepo;

	private List<Empleado> empleados;

	@Test
	public void validarDatosShouldBeOk() {
		Empleado empleado = new Empleado();
		empleado.setNroLegajo(100);
		empleado.setNombre("empleado test");
		empleado.setApellido("apellido test");
		empleado.setActivo(true);
		empleado.setEnPlanta(true);
		
		when(empleadoRepo.existsById(100)).thenReturn(true);
		
		var result = empleadoSrv.validarDatos(empleado);
		
		assertEquals(true, result);
	}
	
	@Test
	public void validarDatosShouldBeFalse() {
		Empleado empleado = new Empleado();
		empleado.setNroLegajo(100);
		empleado.setNombre("empleado test");
		empleado.setApellido("apellido test");
		empleado.setActivo(true);
		empleado.setEnPlanta(true);
		
		when(empleadoRepo.existsById(100)).thenReturn(false);
		
		var result = empleadoSrv.validarDatos(empleado);
		
		assertEquals(false, result);
	}
	
	@Test
	public void obtenerDisponiblesShouldReturn1() {
		Empleado empleado = new Empleado();
		empleado.setNroLegajo(100);
		empleado.setNombre("empleado test");
		empleado.setApellido("apellido test");
		empleado.setActivo(true);
		empleado.setEnPlanta(true);
		
		empleados = new ArrayList<Empleado>();
		empleados.add(empleado);
		
		when(empleadoRepo.findAllDisponibles()).thenReturn(empleados);
		
		var result = empleadoSrv.obtenerDisponibles();
		
		assertEquals(1, result.size());
	}

	@Test
	public void marcarEmpleadoEnPlantaShouldReturnOk() {
		Empleado empleado = new Empleado();
		empleado.setNroLegajo(100);
		empleado.setNombre("empleado test");
		empleado.setApellido("apellido test");
		empleado.setActivo(true);
		empleado.setEnPlanta(false);
		
		when(empleadoRepo.getById(empleado.getNroLegajo())).thenReturn(empleado);
		when(empleadoRepo.save(empleado)).thenReturn(empleado);

		var result = empleadoSrv.marcarEmpleadoEnPlanta(100);
		
		assertEquals(null, result);
		assertEquals(empleado.getEnPlanta(), true);
	}
	
	@Test
	public void marcarEmpleadoEgresadoDePlantaShouldReturnOk() {
		Empleado empleado = new Empleado();
		empleado.setNroLegajo(100);
		empleado.setNombre("empleado test");
		empleado.setApellido("apellido test");
		empleado.setActivo(true);
		empleado.setEnPlanta(true);
		
		Empleado empleadoEgresado = empleado;
		empleadoEgresado.setEnPlanta(false);
		
		when(empleadoRepo.getById(empleado.getNroLegajo())).thenReturn(empleado);
		when(empleadoRepo.save(empleado)).thenReturn(empleadoEgresado);

		var result = empleadoSrv.marcarEmpleadoEgresadoDePlanta(100);
		
		assertEquals(null, result);
		assertEquals(empleado.getEnPlanta(), false);
	}
	
	@Test
	public void editarEmpleadoShouldSetOk() {
		Empleado empleado = new Empleado();
		empleado.setNroLegajo(100);
		empleado.setNombre("empleado test");
		empleado.setApellido("apellido test");
		empleado.setActivo(true);
		empleado.setEnPlanta(true);
		
		SectorTrabajo sector = new SectorTrabajo();
		sector.setIdSector(10);
		sector.setActivo(true);
		sector.setSector("sector test");
		
		Empleado empleadoEdited = new Empleado();
		empleadoEdited.setNroLegajo(100);
		empleadoEdited.setNombre("empleado test");
		empleadoEdited.setApellido("apellido test");
		empleadoEdited.setActivo(true);
		empleadoEdited.setImagen("imagenTest.jpg");
		empleadoEdited.setSector(sector);
		empleadoEdited.setEnPlanta(true);
		
		when(empleadoRepo.getById(empleado.getNroLegajo())).thenReturn(empleado);
		when(empleadoRepo.save(empleado)).thenReturn(empleadoEdited);
		
		//TODO: revisar test
		//var result = empleadoSrv.editarEmpleado(empleadoEdited, 10);
		
		//assertEquals(empleadoEdited, result);
	}
	
	@Test
	public void buscarEmpleadoShouldBeOk() {
		Empleado empleado = new Empleado();
		empleado.setNroLegajo(100);
		empleado.setNombre("empleado test");
		empleado.setApellido("apellido test");
		empleado.setActivo(true);
		empleado.setEnPlanta(true);
		Optional<Empleado> empleadoOpt = Optional.of(empleado);
		
		when(empleadoRepo.findById(empleado.getNroLegajo())).thenReturn(empleadoOpt);
		
		var result = empleadoSrv.buscarEmpleado(empleado.getNroLegajo());
		
		assertEquals(empleadoOpt, result);
	}
	/*
	 * 
	 * 
	 * @Override public Empleado editarEmpleado(Empleado empleado, int idSector) {
	 * 
	 * Empleado empActual = empleadoRepo.getById(empleado.getNroLegajo());
	 * 
	 * if (empleado.getImagen() == null) {
	 * 
	 * empleado.setImagen(empActual.getImagen());
	 * 
	 * } empleado.setEnPlanta(empActual.getEnPlanta());
	 * 
	 * empleado.setSector(sectorRepo.getById(idSector));
	 * 
	 * return empleadoRepo.save(empleado); }
	 * 
	 */
}
