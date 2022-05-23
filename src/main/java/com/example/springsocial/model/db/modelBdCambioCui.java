package com.example.springsocial.model.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@SuppressWarnings("serial")
@Entity
@Table(name="TCAMBIO_CUI")
public class modelBdCambioCui implements Serializable{

	@Id
	@Column(name="idcambio_cui")
	private Long id;
	@Column(name="cui")
	private Long cui;
	@Column(name="serie")
	private Long serie;
	@Column(name="cui_anterior")
	private Long cuianterior;
	@Column(name="solicitud_renap")
	private String numerosolicitud;
	@Column(name="fecha")
	private Date fecha;
	
	
	
	
	public modelBdCambioCui(Long id, Long cui, Long serie, Long cuianterior, String numerosolicitud, Date fecha) {
		super();
		this.id = id;
		this.cui = cui;
		this.serie = serie;
		this.cuianterior = cuianterior;
		this.numerosolicitud = numerosolicitud;
		this.fecha = fecha;
	}
	
	public modelBdCambioCui() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCui() {
		return cui;
	}
	public void setCui(Long cui) {
		this.cui = cui;
	}
	public Long getSerie() {
		return serie;
	}
	public void setSerie(Long serie) {
		this.serie = serie;
	}
	public Long getCuianterior() {
		return cuianterior;
	}
	public void setCuianterior(Long cuianterior) {
		this.cuianterior = cuianterior;
	}
	public String getNumerosolicitud() {
		return numerosolicitud;
	}
	public void setNumerosolicitud(String numerosolicitud) {
		this.numerosolicitud = numerosolicitud;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "modelBdCambioCui [id=" + id + ", cui=" + cui + ", serie=" + serie + ", cuianterior=" + cuianterior
				+ ", numerosolicitud=" + numerosolicitud + ", fecha=" + fecha + "]";
	}
	
}
