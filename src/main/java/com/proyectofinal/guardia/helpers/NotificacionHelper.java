package com.proyectofinal.guardia.helpers;

import java.util.Date;


public class NotificacionHelper {
	
	private int idNotiUsuario;
	private Boolean leido;
	private String mensaje;
	private Date fecha;
	
	public int getIdNotiUsuario() {
		return idNotiUsuario;
	}
	public void setIdNotiUsuario(int idNotiUsuario) {
		this.idNotiUsuario = idNotiUsuario;
	}
	public Boolean getLeido() {
		return leido;
	}
	public void setLeido(Boolean leido) {
		this.leido = leido;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	@Override
	public String toString() {
		return "NotificacionHelper [idNotiUsuario=" + idNotiUsuario + ", leido=" + leido + ", mensaje=" + mensaje + "]";
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
