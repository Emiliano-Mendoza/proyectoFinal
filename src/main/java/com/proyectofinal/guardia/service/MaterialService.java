package com.proyectofinal.guardia.service;

import java.util.List;
import java.util.Optional;

import com.proyectofinal.guardia.domain.Material;

public interface MaterialService {

	public Material crearMaterial(Material material);
	public Material editarMaterial(Material material);
	public List<Material> obtenerTodos();
	public List<Material> obtenerDisponibles();
	public Optional<Material> obtenerPorId(int idMaterial);
}
