package com.proyectofinal.guardia.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Autorizacion")
public class AutorizacionRetiroMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idAutorizacion;
	
	private String descripcion;
	private Date fechaLimite;
	
	@ManyToOne
	@JoinColumn(name = "ID_UsuarioAutorizador")
	private Usuario usuario;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "ID_Retiro")
	private Retiro retiro;
	
	@ManyToOne
	@JoinColumn(name = "ID_Empleado")
	private Empleado empleado;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "materiales_retiro",
		joinColumns = { @JoinColumn(name = "idAutorizacion")},
		inverseJoinColumns = { @JoinColumn (name = "idMaterial")})
	@JsonManagedReference
	private Set<Material> materiales = new HashSet<>();

	public int getIdAutorizacion() {
		return idAutorizacion;
	}

	public void setIdAutorizacion(int idAutorizacion) {
		this.idAutorizacion = idAutorizacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Retiro getRetiro() {
		return retiro;
	}

	public void setRetiro(Retiro retiro) {
		this.retiro = retiro;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Set<Material> getMateriales() {
		return materiales;
	}

	public void setMateriales(Set<Material> materiales) {
		this.materiales = materiales;
	}
			
}
