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

	@Override
	public SectorTrabajo crearSector(SectorTrabajo sector) {
		
		
		 SectorTrabajo sectorAux = sectorRepo.save(sector);
		 sectorAux.getAreas().forEach(a -> { a.setSector(sectorAux); });
		 		
		return sectorRepo.save(sectorAux);
	}

	@Override
	public SectorTrabajo editarSector(SectorTrabajo sectorUpdate) {
		
		SectorTrabajo sector = sectorRepo.findById(sectorUpdate.getIdSector()).orElseThrow();
		sectorUpdate.getAreas().forEach(a -> {a.setSector(sector);});
		
		return sectorRepo.save(sectorUpdate);
	}

}
