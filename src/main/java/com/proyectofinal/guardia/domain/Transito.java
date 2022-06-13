package com.proyectofinal.guardia.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Transito")
public class Transito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int idTransito;
	
	private Date ingreso;
	private Date egreso;
	
	@ManyToOne
	@JoinColumn(name = "id_TipoTransito")
	private TipoTransito tipo;
	
	@ManyToOne
	@JoinColumn(name = "ID_UsuarioIngreso")
	private Usuario usuarioIngreso;
	
	@ManyToOne
	@JoinColumn(name = "ID_UsuarioEgreso")
	private Usuario usuarioEgreso;
	
	@ManyToOne
	@JoinColumn(name = "ID_PrimerVehiculo")
	private Vehiculo primerVehiculo;
	
	@ManyToOne
	@JoinColumn(name = "ID_SegundoVehiculo")
	private Vehiculo segundoVehiculo;
	
	@ManyToOne
	@JoinColumn(name = "ID_Empleado")
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name = "ID_Asistencia")
	private Asistencia asistencia;

	public int getIdTransito() {
		return idTransito;
	}

	public void setIdTransito(int idTransito) {
		this.idTransito = idTransito;
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

	public TipoTransito getTipo() {
		return tipo;
	}

	public void setTipo(TipoTransito tipo) {
		this.tipo = tipo;
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

	public Vehiculo getPrimerVehiculo() {
		return primerVehiculo;
	}

	public void setPrimerVehiculo(Vehiculo primerVehiculo) {
		this.primerVehiculo = primerVehiculo;
	}

	public Vehiculo getSegundoVehiculo() {
		return segundoVehiculo;
	}

	public void setSegundoVehiculo(Vehiculo segundoVehiculo) {
		this.segundoVehiculo = segundoVehiculo;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Asistencia getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(Asistencia asistencia) {
		this.asistencia = asistencia;
	}
	
	
	
}
