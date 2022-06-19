package com.proyectofinal.guardia.domain;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "NotiUsuario")
@Table(name = "Noti_Usuario")
public class NotiUsuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private Boolean leido;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "noti_id")
	@JsonIgnore
    private Notificacion notificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Usuario usuarioNotificado;		
    
    public NotiUsuario() {}
    
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
