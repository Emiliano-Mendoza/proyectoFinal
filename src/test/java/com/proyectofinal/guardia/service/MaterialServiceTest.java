package com.proyectofinal.guardia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.proyectofinal.guardia.dao.MaterialJPARepository;
import com.proyectofinal.guardia.domain.Material;

@SpringBootTest
public class MaterialServiceTest {
	
	@Autowired
	private MaterialService materialSrv;
	
	@MockBean
	private MaterialJPARepository materialRepo;
	
	private List<Material> papelDisponible;

	@Test
	public void crearMaterialShouldReturnOk() {
		//TODO
	}
	 @Test
	 public void editarMaterialShouldReturnOk() {
		 //TODO
	 }
	 
	 @Test
	 public void obtenerTodosShouldReturn1() {
		 Material papel = new Material();
		 papel.setIdMaterial(1);
		 papel.setActivo(true);
		 papel.setMaterial("papel");
		 papelDisponible.add(papel);
		 		 
		 when(materialRepo.findAllDisponibles()).thenReturn(papelDisponible);
		 
		 var resultado = materialSrv.obtenerDisponibles();
		 
		 assertEquals(1, resultado.size());
	 }
	 
	 @Test
	 public void obtenerTodosShouldReturnEmpty() {
		 papelDisponible = new ArrayList<Material>();
		 when(materialRepo.findAllDisponibles()).thenReturn(papelDisponible);
		 
		 var resultado = materialSrv.obtenerDisponibles();
		 
		 assertEquals(0, resultado.size());
	 }
}
