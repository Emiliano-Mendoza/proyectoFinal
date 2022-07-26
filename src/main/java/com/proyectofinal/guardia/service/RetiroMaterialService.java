package com.proyectofinal.guardia.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.proyectofinal.guardia.domain.AutorizacionRetiroMaterial;
import com.proyectofinal.guardia.domain.Retiro;


public interface RetiroMaterialService {
	
	public AutorizacionRetiroMaterial crearAutorizacion(int nroLegajo, List<Integer> materiales, String fechaLimite, String descripcion) throws ParseException;
	public Retiro registrarRetiro(int idAutorizacion, Date fechaRetiro, String observacion, String planta);
	public List<AutorizacionRetiroMaterial> obtenerAutorizacionesActivas();
	public List<AutorizacionRetiroMaterial> obtenerAutorizaciones();
	public List<AutorizacionRetiroMaterial> filtrarAutorizaciones(String fechaLimiteInicio, String fechaLimiteFin, String fechaRetiroInicio,
			String fechaRetiroFin, int nroLegajo, int idAutorizante, int idGuardia, int idMateria) throws ParseException;
	
}
