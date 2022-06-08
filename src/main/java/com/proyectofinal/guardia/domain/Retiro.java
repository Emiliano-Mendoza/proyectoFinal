package com.proyectofinal.guardia.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Retiro")
public class Retiro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idRetiro;
	
	private Date fechaRetiro;
	private String observacion;
	private String planta; 
	
	@ManyToOne
	@JoinColumn(name = "ID_UsuarioAutorizado")
	private Usuario usuario;

	public int getIdRetiro() {
		return idRetiro;
	}

	public void setIdRetiro(int idRetiro) {
		this.idRetiro = idRetiro;
	}

	public Date getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
