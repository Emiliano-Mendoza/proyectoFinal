package com.proyectofinal.guardia.domain;


import java.util.Date;


import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;



import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Notificacion")
public class Notificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idNotificacion;
	
	@NotEmpty
	private String descripcion;
	@NotEmpty
	private String tipo;
	
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "ID_Notificante")
	private Usuario notificante;
	
	
	@OneToMany(mappedBy = "notificacion", cascade = CascadeType.ALL)
	@JsonIgnore
    private Set<NotiUsuario> notiUsuario;
	
	
	 public Notificacion(String descripcion, String tipo, Usuario notificante, Date fecha, NotiUsuario... notiUsuario) {
	        this.descripcion = descripcion;
	        this.tipo = tipo;
	        this.notificante = notificante;
	        this.fecha = fecha;
	        for(NotiUsuario nu : notiUsuario) nu.setNotificacion(this);
	        this.notiUsuario = Stream.of(notiUsuario).collect(Collectors.toSet());
	 }
	
	public Notificacion() {}
	
	/*
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "notificacion_usuarios",
		joinColumns = { @JoinColumn(name = "idNotificacion")},
		inverseJoinColumns = { @JoinColumn (name = "idUsuario")})
	@JsonManagedReference
	private Set<Usuario> usuariosNotifiacados = new HashSet<>(); */

	public int getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(int idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public Usuario getNotificante() {
		return notificante;
	}

	public void setNotificante(Usuario notificante) {
		this.notificante = notificante;
	}

	/*	
	public Set<NotiUsuario> getNotiUsuario() {
		return notiUsuario;
	}

	public void setNotiUsuario(Set<NotiUsuario> notiUsuario) {
		this.notiUsuario = notiUsuario;
	}
	*/
	
	
	
	@Override
	public String toString() {
		return "Notificacion [idNotificacion=" + idNotificacion + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", notificante=" + notificante + "]";
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



}


