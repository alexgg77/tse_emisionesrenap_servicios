package com.example.springsocial.model.input;

import java.util.ArrayList;


public class ReporteRenap {
	
	private String codigoRespuesta;

	private String  mensajeRespuesta;

	private String  reportePDF;

	private Long  departamento;

	private Long municipio;

	private Long sede;

	private String nombreSede;

	private Long correlativoEnvío;

	private Long registros;

	private String fecha;
	
	private ArrayList<fallecidos> zfallecidos;
	

	public ReporteRenap() {
	}

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public String getReportePDF() {
		return reportePDF;
	}

	public Long getDepartamento() {
		return departamento;
	}

	public Long getMunicipio() {
		return municipio;
	}

	public Long getSede() {
		return sede;
	}

	public String getNombreSede() {
		return nombreSede;
	}

	public Long getCorrelativoEnvío() {
		return correlativoEnvío;
	}

	public Long getRegistros() {
		return registros;
	}

	public String getFecha() {
		return fecha;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	public void setReportePDF(String reportePDF) {
		this.reportePDF = reportePDF;
	}

	public void setDepartamento(Long departamento) {
		this.departamento = departamento;
	}

	public void setMunicipio(Long municipio) {
		this.municipio = municipio;
	}

	public void setSede(Long sede) {
		this.sede = sede;
	}

	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}

	public void setCorrelativoEnvío(Long correlativoEnvío) {
		this.correlativoEnvío = correlativoEnvío;
	}

	public void setRegistros(Long registros) {
		this.registros = registros;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public ArrayList<fallecidos> getZfallecidos() {
		return zfallecidos;
	}

	public void setZfallecidos(ArrayList<fallecidos> zfallecidos) {
		this.zfallecidos = zfallecidos;
	}

	
}
