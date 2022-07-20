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
@Table(name="Proveedor")
public class Proveedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idProveedor;
	
	@NotEmpty
	@Column(length = 50, nullable = false)
	@Size(min = 1, max = 50)
	private String proveedor;
	@NotEmpty
	@Column(nullable = false)
	@Size(min = 1)
	private String descripcion;
	private Boolean activo;
	
		
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
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
		return "Proveedor [idProveedor=" + idProveedor + ", proveedor=" + proveedor + ", descripcion=" + descripcion
				+ ", activo=" + activo + "]";
	}
	
	
	
}
