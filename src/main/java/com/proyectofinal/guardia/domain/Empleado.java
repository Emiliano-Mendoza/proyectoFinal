package com.proyectofinal.guardia.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Empleado")
public class Empleado {
	
	@Id
	private int nroLegajo;
		
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String apellido;
	private String imagen;
	private Boolean activo;
	private Boolean enPlanta;
	
	@ManyToOne
	@JoinColumn(name = "id_SectorTrabajo")
	private SectorTrabajo sector;
	
			
	public int getNroLegajo() {
		return nroLegajo;
	}

	public void setNroLegajo(int nroLegajo) {
		this.nroLegajo = nroLegajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public void setSector(SectorTrabajo sector) {
		this.sector = sector;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public SectorTrabajo getSector() {
		return sector;
	}

	public Boolean getEnPlanta() {
		return enPlanta;
	}

	public void setEnPlanta(Boolean enPlanta) {
		this.enPlanta = enPlanta;
	}

	@Override
	public String toString() {
		return "Empleado [nroLegajo=" + nroLegajo + ", nombre=" + nombre + ", apellido=" + apellido + ", imagen="
				+ imagen + ", activo=" + activo + ", enPlanta=" + enPlanta + ", sector=" + sector + "]";
	}


		
	
}
