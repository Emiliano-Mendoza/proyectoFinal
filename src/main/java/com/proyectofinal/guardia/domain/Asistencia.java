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
@Table(name="Asistencia")
public class Asistencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idAsistencia;
	
	private Date ingreso;
	private Date egreso;
	
	@Column(length = 50)
	@Size(max = 50)
	private String planta;
	private Boolean enTransito;
	
	@ManyToOne
	@JoinColumn(name = "ID_Usuario_Ingreso")	
	private Usuario usuarioIngreso;
	
	@ManyToOne
	@JoinColumn(name = "ID_Usuario_Egreso")	
	private Usuario usuarioEgreso;
	
	@ManyToOne
	@JoinColumn(name = "id_Empleado")
	private Empleado empleado;

	public int getIdAsistencia() {
		return idAsistencia;
	}

	public void setIdAsistencia(int idAsistencia) {
		this.idAsistencia = idAsistencia;
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

	public String getPlanta() {
		return planta;
	}

	public void setPlanta(String planta) {
		this.planta = planta;
	}

	public Boolean getEnTransito() {
		return enTransito;
	}

	public void setEnTransito(Boolean enTransito) {
		this.enTransito = enTransito;
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

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@Override
	public String toString() {
		return "Asistencia [idAsistencia=" + idAsistencia + ", ingreso=" + ingreso + ", egreso=" + egreso + ", planta="
				+ planta + ", enTransito=" + enTransito + ", usuarioIngreso=" + usuarioIngreso + ", usuarioEgreso="
				+ usuarioEgreso + ", empleado=" + empleado + "]";
	}
	
		
}
