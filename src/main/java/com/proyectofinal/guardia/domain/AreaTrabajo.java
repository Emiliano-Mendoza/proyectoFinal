package com.proyectofinal.guardia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="AreaTrabajo")
public class AreaTrabajo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idArea;
	
	@Column(length = 50, nullable = false)
	@Size(min = 1, max = 50)
	@NotEmpty
	private String nombreArea;
	@NotEmpty
	private String descripcion;
	private Boolean activo;
	
	@ManyToOne
	@JoinColumn(name = "id_SectorTrabajo")
	@JsonBackReference
	private SectorTrabajo sector; 
	
	public int getIdArea() {
		return idArea;
	}
	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	public String getNombreArea() {
		return nombreArea;
	}
	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "AreaTrabajo [idArea=" + idArea + ", nombreArea=" + nombreArea + ", descripcion=" + descripcion
				+ ", activo=" + activo + "]";
	}
	public SectorTrabajo getSector() {
		return sector;
	}
	public void setSector(SectorTrabajo sector) {
		this.sector = sector;
	}
	
	
}
