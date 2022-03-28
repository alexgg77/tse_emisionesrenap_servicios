package com.example.springsocial.model.input;

import java.util.ArrayList;


public class ReporteRenap {
	
	private String  reportePDF;

	private String  departamento;

	private String municipio;

	private String sede;

	private String nombreSede;

	private String correlativoEnvio;

	private String registros;

	private String fecha;
	
	private ArrayList<fallecidos> fallecidos;
	
	

	public ReporteRenap() {
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

	public ArrayList<fallecidos> getFallecidos() {
		return fallecidos;
	}

	public void setFallecidos(ArrayList<fallecidos> fallecidos) {
		this.fallecidos = fallecidos;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReporteRenap [reportePDF=").append(reportePDF).append(", departamento=").append(departamento)
				.append(", municipio=").append(municipio).append(", sede=").append(sede).append(", nombreSede=")
				.append(nombreSede).append(", correlativoEnvio=").append(correlativoEnvio).append(", registros=")
				.append(registros).append(", fecha=").append(fecha).append("]");
		return builder.toString();
	}
}
