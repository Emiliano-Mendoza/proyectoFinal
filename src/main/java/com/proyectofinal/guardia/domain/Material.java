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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="Material")
public class Material {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idMaterial;
	
	@NotEmpty
	@Column(length = 50, nullable = false)
	@Size(min = 1, max = 50)
	private String material;
	
	private Boolean activo;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "materiales")
	@JsonBackReference
	private Set<AutorizacionRetiroMaterial> retiro = new HashSet<>();
	
	public int getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Set<AutorizacionRetiroMaterial> getRetiro() {
		return retiro;
	}

	public void setRetiro(Set<AutorizacionRetiroMaterial> retiro) {
		this.retiro = retiro;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	
}
