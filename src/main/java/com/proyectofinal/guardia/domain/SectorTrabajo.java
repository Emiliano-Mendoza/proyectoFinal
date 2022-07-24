package com.proyectofinal.guardia.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="SectorTrabajo")
public class SectorTrabajo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idSector;
	
	@Column(length = 50, nullable = false)
	@Size(min = 1, max = 50)
	@NotEmpty
	private String sector;
	
	private Boolean activo;
	
	@OneToMany(mappedBy = "sector", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AreaTrabajo> areas = new ArrayList<>();
	
	
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
		return "SectorTrabajo [idSector=" + idSector + ", sector=" + sector + ", activo=" + activo + ", areas=" + areas
				+ "]";
	}

	public List<AreaTrabajo> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaTrabajo> areas) {
		this.areas = areas;
	}
	
	
}
