package com.example.springsocial.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="TWS_ENCABEZADOFALLE")
@SequenceGenerator(name = "default_gen", sequenceName = "SEQ_IDENCABEZADOFALLE", allocationSize = 1)
public class twsEncabezado implements Serializable{
	
	@GeneratedValue(generator="default_gen")
	@Id
	@Column(name="idencabezadofalle")
	private Long id;
	@Column(name="sede")
	private Long sede;
	@Column(name="correlativoenvio")
	private Long correlativoenvio;
	@Column(name="registros")
	private Long registros;
	@Column(name="registrador")
	private String registrador;
	@Column(name="fechainicio")
	private Date fechainicio;
	@Column(name="fechafin")
	private Date fechafin;
	@Column(name="reportepdf")
	private String rutapdf;
	@Column(name="fechacreacion")
	private Date fechacreacion;
	@Column(name="estadoprocesado")
	private Long estadoprocesado;
	@Column(name="coddepartamento")
	private Long codigodepartamento;
	@Column(name="codmunicipio")
	private Long codigomunicipio;
	
	
	
	public twsEncabezado(Long id, Long sede, Long correlativoenvio, Long registros, String registrador,
			Date fechainicio, Date fechafin, String rutapdf, Date fechacreacion, Long estadoprocesado) {
		this.id = id;
		this.sede = sede;
		this.correlativoenvio = correlativoenvio;
		this.registros = registros;
		this.registrador = registrador;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
		this.rutapdf = rutapdf;
		this.fechacreacion = fechacreacion;
		this.estadoprocesado = estadoprocesado;
	}

	public twsEncabezado() {
	}



	public Long getId() {
		return id;
	}

	public Long getSede() {
		return sede;
	}

	public Long getCorrelativoenvio() {
		return correlativoenvio;
	}

	public Long getRegistros() {
		return registros;
	}

	public String getRegistrador() {
		return registrador;
	}

	public Date getFechainicio() {
		return fechainicio;
	}

	public Date getFechafin() {
		return fechafin;
	}

	public String getRutapdf() {
		return rutapdf;
	}

	public Date getFechacreacion() {
		return fechacreacion;
	}

	public Long getEstadoprocesado() {
		return estadoprocesado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSede(Long sede) {
		this.sede = sede;
	}

	public void setCorrelativoenvio(Long correlativoenvio) {
		this.correlativoenvio = correlativoenvio;
	}

	public void setRegistros(Long registros) {
		this.registros = registros;
	}

	public void setRegistrador(String registrador) {
		this.registrador = registrador;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public void setRutapdf(String rutapdf) {
		this.rutapdf = rutapdf;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public void setEstadoprocesado(Long estadoprocesado) {
		this.estadoprocesado = estadoprocesado;
	}

	public Long getCodigodepartamento() {
		return codigodepartamento;
	}

	public Long getCodigomunicipio() {
		return codigomunicipio;
	}

	public void setCodigodepartamento(Long codigodepartamento) {
		this.codigodepartamento = codigodepartamento;
	}

	public void setCodigomunicipio(Long codigomunicipio) {
		this.codigomunicipio = codigomunicipio;
	}
}
