package com.proyectofinal.guardia.service;

import java.util.List;
import java.util.Optional;

import com.proyectofinal.guardia.domain.Material;

public interface MaterialService {

	public Material crearMaterial(String material, Boolean activo);
	public Material editarMaterial(int idMaterial, String material, Boolean activo);
	public List<Material> obtenerTodos();
	public List<Material> obtenerDisponibles();
	public Optional<Material> obtenerPorId(int idMaterial);
}
