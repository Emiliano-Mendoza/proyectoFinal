package com.proyectofinal.guardia.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idUsuario;
	
	@NotEmpty
	@Size(min = 1, max = 50)
	@Column(unique=true, length = 50, nullable = false)
	private String username;
	
	@NotEmpty
	@JsonIgnore
	@Column(nullable = false)
	@Size(min = 6)
	private String contraseña;	
	private Boolean activo;
	
	@NotEmpty
	@Column(length = 30, nullable = false)
	@Size(min = 1, max = 30)
	private String nombre;
	
	@NotEmpty
	@Column(length = 30, nullable = false)
	@Size(min = 1, max = 30)
	private String apellido;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_roles",
		joinColumns = { @JoinColumn(name = "idUsuario")},
		inverseJoinColumns = { @JoinColumn (name = "idRol")})
	@JsonManagedReference
	private Set<Rol> roles = new HashSet<>();
	
	
	 @OneToMany(mappedBy = "usuarioNotificado", cascade = CascadeType.ALL)
	 @JsonIgnore
	 private Set<NotiUsuario> notiUsuario = new HashSet<>();
	
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
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

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	public String nombreCompleto() {
		return apellido + ' ' +  nombre;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", username=" + username + ", activo=" + activo + ", nombre="
				+ nombre + ", apellido=" + apellido + ", roles=" + roles + "]";
	}

	

	
}
