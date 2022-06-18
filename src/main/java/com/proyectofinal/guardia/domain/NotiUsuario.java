package com.proyectofinal.guardia.domain;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "NotiUsuario")
@Table(name = "Noti_Usuario")
public class NotiUsuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private Boolean leido;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "noti_id")
    private Notificacion notificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private Usuario usuarioNotificado;		
    
             
	public NotiUsuario(Usuario usuarioNotificado, Boolean leido) {
		super();
		this.leido = leido;
		this.usuarioNotificado = usuarioNotificado;
	}

	public void setLeido(Boolean leido) {
		this.leido = leido;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}

	public Usuario getUsuarioNotificado() {
		return usuarioNotificado;
	}

	public void setUsuarioNotificado(Usuario usuarioNotificado) {
		this.usuarioNotificado = usuarioNotificado;
	}

	public Boolean getLeido() {
		return leido;
	}


	@Override
	public String toString() {
		return "NotiUsuario [id=" + id + ", leido=" + leido + "]";
	}

	
}
