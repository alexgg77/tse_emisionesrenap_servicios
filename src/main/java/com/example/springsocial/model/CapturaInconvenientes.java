package com.example.springsocial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="TWS_INCONSISTENCIAFALLE")
@SequenceGenerator(name = "default_gen", sequenceName = "SEQ_IDINCONSISTENCIAFALLE", allocationSize = 1)
public class CapturaInconvenientes {
	
	@GeneratedValue(generator="default_gen")
	@Id
	@Column(name="idinconsistenciafalle")
	private Long id;
	@Column(name="corelativoenvio")
	private Long correlativoenvio;
	@Column(name="cui")
	private Long cui;
	@Column(name="numeroinscripciondefuncion")
	private Long numeroinscripciondefuncion;
	@Column(name="tipoinconsistencia")
	private Long tipoinconsistencia;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="estado")
	private Long estado;
	

	public CapturaInconvenientes() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCorrelativoenvio() {
		return correlativoenvio;
	}


	public void setCorrelativoenvio(Long correlativoenvio) {
		this.correlativoenvio = correlativoenvio;
	}


	public Long getCui() {
		return cui;
	}


	public void setCui(Long cui) {
		this.cui = cui;
	}


	public Long getNumeroinscripciondefuncion() {
		return numeroinscripciondefuncion;
	}


	public void setNumeroinscripciondefuncion(Long numeroinscripciondefuncion) {
		this.numeroinscripciondefuncion = numeroinscripciondefuncion;
	}


	public Long getTipoinconsistencia() {
		return tipoinconsistencia;
	}


	public void setTipoinconsistencia(Long tipoinconsistencia) {
		this.tipoinconsistencia = tipoinconsistencia;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Long getEstado() {
		return estado;
	}


	public void setEstado(Long estado) {
		this.estado = estado;
	}
}
