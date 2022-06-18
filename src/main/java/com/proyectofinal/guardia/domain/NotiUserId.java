package com.proyectofinal.guardia.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NotiUserId implements Serializable {
	
	@Column(name = "id_notificacion")
    private int idNotificacion;
	
	@Column(name = "id_usuario")
    private int idUsuario;
	
	private NotiUserId() {};
	
				
	public NotiUserId(int idNotificacion, int idUsuario) {
		super();
		this.idNotificacion = idNotificacion;
		this.idUsuario = idUsuario;
	}


	public int getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(int idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        NotiUserId that = (NotiUserId) o;
        return Objects.equals(idNotificacion, that.idNotificacion) &&
               Objects.equals(idUsuario, that.idUsuario);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(idNotificacion, idUsuario);
    }


	@Override
	public String toString() {
		return "NotiUserId [idNotificacion=" + idNotificacion + ", idUsuario=" + idUsuario + "]";
	}

	
	
}
