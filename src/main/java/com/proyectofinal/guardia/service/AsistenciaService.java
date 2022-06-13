package com.proyectofinal.guardia.service;

import java.util.Date;
import java.util.List;

import com.proyectofinal.guardia.domain.Asistencia;
import com.proyectofinal.guardia.domain.Empleado;

public interface AsistenciaService {
	
	public Asistencia crearAsistencia(Empleado empleado, Date ingreso, String planta);
	public Asistencia egresoAsistencia(int idAsistencia, Date egreso);
	public List<Asistencia> listarAsistenciasSinEgresoSinTransito();
	public List<Asistencia> listarAsistenciasSinEgreso();
	public void marcarAsistenciaEnTransito(int idAsistencia);
	public void removerMarcarEnTransito(int idAsistencia);
		
}
