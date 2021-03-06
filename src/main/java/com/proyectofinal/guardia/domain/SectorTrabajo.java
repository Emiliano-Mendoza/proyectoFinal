package com.proyectofinal.guardia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="SectorTrabajo")
public class SectorTrabajo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idSector;
	
	@Column(length = 50, nullable = false)
	@Size(min = 1, max = 50)
	private String sector;
	
	private Boolean activo;

	public int getIdSector() {
		return idSector;
	}

	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "SectorTrabajo [idSector=" + idSector + ", sector=" + sector + ", activo=" + activo + "]";
	}
	
	
}
