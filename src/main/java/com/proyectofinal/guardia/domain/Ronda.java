package com.proyectofinal.guardia.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="Ronda")
public class Ronda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idRonda;
	
	private Date fechaRonda;
	@NotEmpty
	@Column(length = 30)
	@Pattern(regexp = "[0-9]{2}:[0-9]{2}")
	private String ronda;
	
	@NotEmpty
	private String descripcion;
	@Column(length = 50)
	@Size(max = 50)
	private String planta;
	
	@ManyToOne
	@JoinColumn(name = "ID_UsuarioGuardia")
	private Usuario usuario;
	
	public int getIdRonda() {
		return idRonda;
	}
	public void setIdRonda(int idRonda) {
		this.idRonda = idRonda;
	}
	public Date getFechaRonda() {
		return fechaRonda;
	}
	public void setFechaRonda(Date fechaRonda) {
		this.fechaRonda = fechaRonda;
	}
	public String getRonda() {
		return ronda;
	}
	public void setRonda(String ronda) {
		this.ronda = ronda;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	@Override
	public String toString() {
		return "Ronda [idRonda=" + idRonda + ", fechaRonda=" + fechaRonda + ", ronda=" + ronda + ", descripcion="
				+ descripcion + ", planta=" + planta + ", usuario=" + usuario + "]";
	}
		
	
}
