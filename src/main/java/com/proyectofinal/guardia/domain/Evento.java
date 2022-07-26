package com.proyectofinal.guardia.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="Evento")
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEvento;
	@NotEmpty
	private String descripcion;
	private Date fechaEvento;
	private String observacion;
	private Boolean ocurrencia;
	private Boolean cancelado;
	private String motivoCancelacion;
	
	@ManyToOne
	@JoinColumn(name = "ID_Notificador")
	private Usuario notificador;
	@ManyToOne
	@JoinColumn(name = "ID_Guardia")
	private Usuario guardia;
	
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaEvento() {
		return fechaEvento;
	}
	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Boolean getOcurrencia() {
		return ocurrencia;
	}
	public void setOcurrencia(Boolean ocurrencia) {
		this.ocurrencia = ocurrencia;
	}
	public Boolean getCancelado() {
		return cancelado;
	}
	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado;
	}

	public Usuario getNotificador() {
		return notificador;
	}
	public void setNotificador(Usuario notificador) {
		this.notificador = notificador;
	}
	public Usuario getGuardia() {
		return guardia;
	}
	public void setGuardia(Usuario guardia) {
		this.guardia = guardia;
	}
	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}
	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}
	
	@Override
	public String toString() {
		return "Evento [idEvento=" + idEvento + ", descripcion=" + descripcion + ", fechaEvento=" + fechaEvento
				+ ", observacion=" + observacion + ", ocurrencia=" + ocurrencia + ", cancelado=" + cancelado
				+ ", motivoCancelacion=" + motivoCancelacion + ", notificador=" + notificador + ", guardia=" + guardia
				+ "]";
	}
	
		
}
