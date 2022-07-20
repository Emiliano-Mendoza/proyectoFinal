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
import javax.validation.constraints.Size;


@Entity
@Table(name="IngresoProveedor")
public class IngresoProveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idIngreso;
	
	private Date ingreso;
	private Date egreso;
	
	@Column(length = 60)
	@Size(max = 60)
	private String nombreChofer;
	
	@Column(length = 30)
	@Size(max = 30)
	private String patenteVehiculo;
	
	@Column(length = 50)
	@Size(max = 50)
	private String planta;
	
	@ManyToOne
	@JoinColumn(name = "ID_Usuario_Ingreso")	
	private Usuario usuarioIngreso;
	
	@ManyToOne
	@JoinColumn(name = "ID_Usuario_Egreso")	
	private Usuario usuarioEgreso;
	
	@ManyToOne
	@JoinColumn(name = "id_Proveedor")
	private Proveedor proveedor;

	public int getIdIngreso() {
		return idIngreso;
	}

	public void setIdIngreso(int idIngreso) {
		this.idIngreso = idIngreso;
	}

	public Date getIngreso() {
		return ingreso;
	}

	public void setIngreso(Date ingreso) {
		this.ingreso = ingreso;
	}

	public Date getEgreso() {
		return egreso;
	}

	public void setEgreso(Date egreso) {
		this.egreso = egreso;
	}

	public String getNombreChofer() {
		return nombreChofer;
	}

	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}

	public String getPatenteVehiculo() {
		return patenteVehiculo;
	}

	public void setPatenteVehiculo(String patenteVehiculo) {
		this.patenteVehiculo = patenteVehiculo;
	}

	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	public Usuario getUsuarioIngreso() {
		return usuarioIngreso;
	}

	public void setUsuarioIngreso(Usuario usuarioIngreso) {
		this.usuarioIngreso = usuarioIngreso;
	}

	public Usuario getUsuarioEgreso() {
		return usuarioEgreso;
	}

	public void setUsuarioEgreso(Usuario usuarioEgreso) {
		this.usuarioEgreso = usuarioEgreso;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@Override
	public String toString() {
		return "IngresoProveedor [idIngreso=" + idIngreso + ", ingreso=" + ingreso + ", egreso=" + egreso
				+ ", nombreChofer=" + nombreChofer + ", patenteVehiculo=" + patenteVehiculo + ", planta=" + planta
				+ ", usuarioIngreso=" + usuarioIngreso + ", usuarioEgreso=" + usuarioEgreso + ", proveedor=" + proveedor
				+ "]";
	}
		
}
