package com.proyectofinal.guardia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="Vehiculo")
public class Vehiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idVehiculo;
	
	@NotEmpty
	@Column(length = 40, nullable = false)
	@Size(min = 1, max = 40)
	private String marca;
	
	@NotEmpty
	@Column(length = 40, nullable = false)
	@Size(min = 1, max = 40)
	private String modelo;
	
	@NotEmpty
	@Column(length = 40, nullable = false)
	@Size(min = 1, max = 40)
	private String patente;
	private Boolean activo;
	
	
	public int getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	@Override
	public String toString() {
		return "Vehiculo [idVehiculo=" + idVehiculo + ", marca=" + marca + ", modelo=" + modelo + ", patente=" + patente
				+ ", activo=" + activo + "]";
	}
	
	
}
