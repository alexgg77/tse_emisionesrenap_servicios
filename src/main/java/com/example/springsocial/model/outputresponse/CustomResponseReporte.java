package com.example.springsocial.model.outputresponse;

import java.util.List;
import com.example.springsocial.model.twsDetalle;
import com.example.springsocial.model.twsEncabezado;

public class CustomResponseReporte {
	
	private twsEncabezado encabezadoModel;
	private List<twsDetalle> listadoFallecidos;
	
	
	public twsEncabezado getEncabezadoModel() {
		return encabezadoModel;
	}
	public List<twsDetalle> getListadoFallecidos() {
		return listadoFallecidos;
	}
	public void setEncabezadoModel(twsEncabezado encabezadoModel) {
		this.encabezadoModel = encabezadoModel;
	}
	public void setListadoFallecidos(List<twsDetalle> listadoFallecidos) {
		this.listadoFallecidos = listadoFallecidos;
	}
}
