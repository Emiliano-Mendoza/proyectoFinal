package com.proyectofinal.guardia.service;

import java.util.Date;
import java.util.List;

import com.proyectofinal.guardia.domain.AutorizacionRetiroMaterial;
import com.proyectofinal.guardia.domain.Empleado;
import com.proyectofinal.guardia.domain.Retiro;


public interface RetiroMaterialService {
	
	public AutorizacionRetiroMaterial crearAsistencia(Empleado empleado, List<Integer> materiales, String fechaLimite, String descripcion);
	public Retiro registrarRetiro(int idAutorizacion, Date fechaRetiro, String observacion, String planta);
	public List<AutorizacionRetiroMaterial> obtenerAutorizacionesActivas();
	public List<AutorizacionRetiroMaterial> obtenerAutorizaciones();
	
}
