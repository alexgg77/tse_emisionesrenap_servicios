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

	private String correlativoEnvio;

	private String registros;
	
	private String registrador;

	private String fechainicio;
	
	private String fechafin;
	
	private ArrayList<fallecidos> fallecidos;
	
	

	public ReporteRenap() {
	}

	public String getRegistrador() {
		return registrador;
	}

	public void setRegistrador(String registrador) {
		this.registrador = registrador;
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

	public String getCorrelativoEnvio() {
		return correlativoEnvio;
	}

	public String getRegistros() {
		return registros;
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

	public void setCorrelativoEnvio(String correlativoEnvio) {
		this.correlativoEnvio = correlativoEnvio;
	}

	public void setRegistros(String registros) {
		this.registros = registros;
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

	public ArrayList<fallecidos> getFallecidos() {
		return fallecidos;
	}

	public void setFallecidos(ArrayList<fallecidos> fallecidos) {
		this.fallecidos = fallecidos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReporteRenap [codigoRespuesta=").append(codigoRespuesta).append(", mensajeRespuesta=")
				.append(mensajeRespuesta).append(", reportePDF=").append(reportePDF).append(", departamento=")
				.append(departamento).append(", municipio=").append(municipio).append(", sede=").append(sede)
				.append(", nombreSede=").append(nombreSede).append(", correlativoEnvio=").append(correlativoEnvio)
				.append(", registros=").append(registros).append(", registrador=").append(registrador)
				.append(", fechainicio=").append(fechainicio).append(", fechafin=").append(fechafin).append("]");
		return builder.toString();
	}
}
