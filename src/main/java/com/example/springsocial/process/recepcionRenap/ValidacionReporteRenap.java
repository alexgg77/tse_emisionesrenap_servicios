package com.example.springsocial.process.recepcionRenap;

import java.util.Date;
import com.alibaba.fastjson.JSONObject;
import com.example.springsocial.model.CapturaInconvenientes;
import com.example.springsocial.tools.DateTools;

@SuppressWarnings({"rawtypes","unchecked","unused"})
public class ValidacionReporteRenap {
	private Integer numero = null;
	private Date fecha = null;
	private DateTools dateTools = new DateTools();
	private CapturaInconvenientes captura;
	private JSONObject json, jsonEncabezado;
	
	private String [] keys = new String[] {
			"cui",
			"fecha_defuncion",
			"fecha_inscripcion_defuncion",
			"orden_cedula",
			"registro_cedula",
			"depto_extension",
			"munic_extension",
			"fecha_nacimiento",
			"pais_nacimiento",
			"depto_nacimiento",
			"munic_nacimiento",
			"genero",
			"primer_nombre",
			"segundo_nombre",
			"tercer_nombre",
			"primer_apellido",
			"segundo_apellido",
			"apellido_casada",
			"depto_inscripcion",
			"munic_inscripcion",
			"numero_inscripcion_defuncion",
			"pais_defuncion",
			"depto_defuncion",
			"munic_defuncion",
			"causa_defuncion",
			"primer_nombre_madre",
			"segundo_nombre_madre",
			"tercer_nombre_madre",
			"primer_apellido_madre",
			"segundo_apellido_madre",
			"apellido_casada_madre",
			"primer_nombre_padre",
			"segundo_nombre_padre",
			"tercer_nombre_padre",
			"primer_apellido_padre",
			"segundo_apellido_padre",
			"folio",
			"partida",
			"libro"
	};
	
	private String [] tipos = new String[] {
			"numero",
			"fecha",
			"fecha",
			"cadena",
			"numero",
			"numero",
			"numero",
			"fecha",
			"numero",
			"numero",
			"numero",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"numero",
			"numero",
			"numero",
			"numero",
			"numero",
			"numero",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena",
			"cadena"
	};
	
	private String [] tamaños = new String[] {
			"13",
			"no",
			"no",
			"4",
			"9",
			"3",
			"3",
			"no",
			"4",
			"3",
			"3",
			"1",
			"25",
			"25",
			"50",
			"50",
			"25",
			"25",
			"3",
			"3",
			"10",
			"4",
			"3",
			"3",
			"1000",
			"25",
			"25",
			"50",
			"50",
			"25",
			"25",
			"25",
			"25",
			"50",
			"50",
			"50",
			"10",
			"10",
			"10"
	};
	

	public void setJSON(JSONObject json, JSONObject jsonEncabezado) {
		this.json = json;
		this.jsonEncabezado = jsonEncabezado;
	}
	
	public void validarJson() {
		
		for(int i=0;i<keys.length;i++) {
			validacion(tipos[i],json.getString(keys[i]),tamaños[i],keys[i]);
		}
		
	}
	
	public void validacion(String tipo,String valor,String tamaño,String key) {
		switch(tipo) {
		case "numero":
			verificarnumero(valor,key);
			break;
		case "cadena":
			verificarcadena(valor, tamaño,key);
			break;
		case "fecha":
			verificarfecha(valor,key);
			break;
		}
	}
	
	Integer  verificarnumero(String valor, String key){
		captura = new CapturaInconvenientes();
		Integer numero=null ;
		try {
			if(valor!=null) {
				numero = Integer.valueOf(valor);
			}else {
				captura.setCorrelativoenvio(jsonEncabezado.getString("correlativoEnvio"));
				captura.setCui(json.getString("cui"));
				captura.setDescripcion(key);
				captura.setNumeroinscripciondefuncion(json.getString("numero_inscripcion_defuncion"));
				captura.setTipoinconsistencia("2");
			}
		}catch(Exception e) {
			numero = null;
		}
		
		return numero;
	}
	
	String  verificarcadena(String valor,String tamaño, String key){
		captura = new CapturaInconvenientes();
		String cadena = null;
		try {
			if(valor!=null) {
				if(valor.length()>Integer.valueOf(tamaño)) {
					
				}
			}else {
				
			}
		}catch(Exception e) {
			cadena = null;
		}
		
		return cadena;
	}
	
	Date verificarfecha(String valor, String key) {
		captura = new CapturaInconvenientes();
		Date fecha = null;
		
		if(valor!=null) {
			fecha = dateTools.fechaFormatoWs(valor);
		}else {
			
		}
		
		return fecha;
	}
	
}

/*
if(json.get("fecha_inscripcion_defuncion")!=null) {	
	fecha = dateTools.fechaFormatoWs(json.getString("fecha_inscripcion_defuncion"));
}
if(json.get("depto_inscripcion")!=null) {	
	
}
if(json.get("munic_inscripcion")!=null) {	
	
}
if(json.get("numero_inscripcion_defuncion")!=null) {	
	
}
 * */
