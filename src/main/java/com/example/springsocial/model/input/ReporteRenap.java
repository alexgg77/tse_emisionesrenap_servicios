package com.example.springsocial.model.input;

import java.util.ArrayList;


public class ReporteRenap {
	
	private String codigoRespuesta;

	private String  mensajeRespuesta;

	private String  reportePDF;

	private String  departamento;

	private String municipio;

	private String sede;

	private String nombreSede;

	private String correlativoEnvío;

	private String registros;

	private String fechainicio;
	
	private String fechafin;
	
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

	public String getDepartamento() {
		return departamento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public String getSede() {
		return sede;
	}

	public String getNombreSede() {
		return nombreSede;
	}

	public String getCorrelativoEnvío() {
		return correlativoEnvío;
	}

	public String getRegistros() {
		return registros;
	}

	public ArrayList<fallecidos> getZfallecidos() {
		return zfallecidos;
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

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}

	public void setCorrelativoEnvío(String correlativoEnvío) {
		this.correlativoEnvío = correlativoEnvío;
	}

	public void setRegistros(String registros) {
		this.registros = registros;
	}

	public void setZfallecidos(ArrayList<fallecidos> zfallecidos) {
		this.zfallecidos = zfallecidos;
	}

	public String getFechainicio() {
		return fechainicio;
	}

	public String getFechafin() {
		return fechafin;
	}

	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}

	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}
}
