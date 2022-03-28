package com.example.springsocial.model.outputresponse;

import java.util.Date;

public class ResponseClassOperaciones {
	
	private String base64;
	private Date fechafin;
	private Date fechainicio;
	private Integer total;
	
	
	public ResponseClassOperaciones() {
	}
	public String getBase64() {
		return base64;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
	
}
