package com.proyectofinal.guardia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.SectorTrabajoJPARepository;
import com.proyectofinal.guardia.domain.SectorTrabajo;
import com.proyectofinal.guardia.service.SectorTrabajoService;

@Service
public class SectorTrabajoServiceImpl implements SectorTrabajoService {
	
	@Autowired
	private SectorTrabajoJPARepository sectorRepo;
	
	@Override
	public List<SectorTrabajo> obtenerTodos() {
		
		return sectorRepo.findAll();
	}

	@Override
	public List<SectorTrabajo> obtenerDisponibles() {
		
		return sectorRepo.findAllActivos();
	}

}
