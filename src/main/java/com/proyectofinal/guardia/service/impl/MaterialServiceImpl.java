package com.proyectofinal.guardia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.MaterialJPARepository;
import com.proyectofinal.guardia.domain.Material;
import com.proyectofinal.guardia.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {
	
	@Autowired
	private MaterialJPARepository materialRepo;
	
	@Override
	public Material crearMaterial(String material, Boolean activo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Material editarMaterial(int idMaterial, String material, Boolean activo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Material> obtenerTodos() {

		return materialRepo.findAll();
	}

	@Override
	public List<Material> obtenerDisponibles() {

		return materialRepo.findAllDisponibles();
	}

	@Override
	public Optional<Material> obtenerPorId(int idMaterial) {
		// TODO Auto-generated method stub
		return null;
	}

}
