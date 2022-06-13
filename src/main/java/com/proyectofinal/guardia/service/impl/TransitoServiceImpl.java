package com.proyectofinal.guardia.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.AsistenciaJPARepository;
import com.proyectofinal.guardia.dao.TipoTransitoJPARepository;
import com.proyectofinal.guardia.dao.TransitoJPARepository;
import com.proyectofinal.guardia.domain.Asistencia;
import com.proyectofinal.guardia.domain.Transito;
import com.proyectofinal.guardia.service.TransitoService;

@Service
public class TransitoServiceImpl implements TransitoService {
	
	@Autowired
	private TransitoJPARepository transitoRepo;
	
	@Autowired
	private TipoTransitoJPARepository tipoRepo;
	
	@Autowired
	private AsistenciaJPARepository asisRepo;
	
	@Override
	public Transito crearTransito(int idAsistencia, Date salida) {

		Transito transito = new Transito();
		Asistencia asis = asisRepo.getById(idAsistencia);
		
		transito.setAsistencia(asis);
		transito.setEgreso(salida);
		transito.setEmpleado(asis.getEmpleado());
		transito.setTipo(tipoRepo.getById(1));
		
		return transitoRepo.save(transito);
	}

	@Override
	public Transito reingresoTransito(int idTransito, Date reingreso) {

		Transito transito = transitoRepo.getById(idTransito);		
		transito.setIngreso(reingreso);
		
		return transitoRepo.save(transito);
		
	}

	@Override
	public List<Transito> obtenerTransitos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transito> obtenerTransitosActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
