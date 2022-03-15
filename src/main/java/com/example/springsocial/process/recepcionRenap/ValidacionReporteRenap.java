package com.example.springsocial.process.recepcionRenap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import com.alibaba.fastjson.JSONObject;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.model.CapturaInconvenientes;
import com.example.springsocial.model.input.ReporteRenap;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.DateTools;
import com.fasterxml.jackson.core.JsonProcessingException;

@SuppressWarnings({"rawtypes","unchecked","unused"})
public class ValidacionReporteRenap {
	private EntityManagerFactory entityManagerFactory;
	private EntityTransaction transaction  = null;
	private EntityManager entityManager = null;
	private Logger logger = Logger.getLogger(ValidacionReporteRenap.class.getName());
	private Integer numero = null;
	private Date fecha = null;
	private DateTools dateTools = new DateTools();
	private ObjectSetGet data= new ObjectSetGet();
	private UserPrincipal userPrincipal =null;
	private CapturaInconvenientes captura;
	private List<CapturaInconvenientes> listacaptura;
	private List<Integer> listadoposiciones;
	private JSONObject json, jsonEncabezado;
	private boolean control = false, bandera=false;
	
	public void setData(ReporteRenap element) {data.setObject(element);}
	public void setUserPrincipal(UserPrincipal userPrincipal) {this.userPrincipal=userPrincipal;}
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {if(entityManagerFactory!=null) this.entityManagerFactory=entityManagerFactory;}
	
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
	
	public List<CapturaInconvenientes> getListaInconvenientes(){
		return listacaptura;
	}
	
	public List<Integer> getListadoPosiciones(){
		return listadoposiciones;
	}
	
	public void init() throws JsonProcessingException {
		listadoposiciones = new ArrayList<Integer>();
		listacaptura = new ArrayList<CapturaInconvenientes>();
		this.jsonEncabezado=data.convertAtJSONTYPE(JSONObject.class);
	}
	
	private void validarJson() {
		for(int i=0;i<jsonEncabezado.getJSONArray("fallecidos").size();i++) {
			json = (JSONObject) jsonEncabezado.getJSONArray("fallecidos").get(i);
			for(int f =0;f<keys.length;f++) {
				validacion(tipos[f],json.getString(keys[f]),tamaños[f],keys[f],i);
				if(control==true && bandera==false) {
					bandera = true;
					listadoposiciones.add(i);
				}
			}
			bandera = false;
			control = false;
		}
	}
	
	public void validacion(String tipo,String valor,String tamaño,String key,int posicion) {
		switch(tipo) {
		case "numero":
			verificarnumero(valor,key,posicion);
			break;
		case "cadena":
			verificarcadena(valor, tamaño,key,posicion);
			break;
		case "fecha":
			verificarfecha(valor,key,posicion);
			break;
		}
	}
	
	void  verificarnumero(String valor, String key,int posicion){
		captura = new CapturaInconvenientes();
		Long numero=null ;
		try {
			if(valor!=null && valor.length()>0) {
				numero = Long.valueOf(valor);
			}else {
				if(key.equals("depto_inscripcion") || key.equals("munic_inscripcion") || key.equals("numero_inscripcion_defuncion")) {
					control = true;
					captura.setCorrelativoenvio(jsonEncabezado.getLong("correlativoEnvio"));
					captura.setCui(json.getLong("cui"));
					captura.setDescripcion("Campo: "+key+" valor:"+valor);
					captura.setNumeroinscripciondefuncion(json.getLong("numero_inscripcion_defuncion"));
					captura.setTipoinconsistencia(2l);
					captura.setEstado(0l);
					listacaptura.add(captura);
				}
			}
		}catch(Exception e) {
			control = true;
			captura.setCorrelativoenvio(jsonEncabezado.getLong("correlativoEnvio"));
			captura.setCui(json.getLong("cui"));
			captura.setDescripcion("Campo: "+key+" valor:"+valor);
			captura.setNumeroinscripciondefuncion(json.getLong("numero_inscripcion_defuncion"));
			captura.setTipoinconsistencia(3l);
			captura.setEstado(0l);
			
			listacaptura.add(captura);
		}
		
	}
	
	void  verificarcadena(String valor,String tamaño, String key,int posicion){
		captura = new CapturaInconvenientes();
		String cadena = null;

		if(valor!=null && valor.length()>0) {
			if(valor.length()>Integer.valueOf(tamaño)) {
				control = true;
				captura.setCorrelativoenvio(jsonEncabezado.getLong("correlativoEnvio"));
				captura.setCui(json.getLong("cui"));
				captura.setDescripcion("Campo: "+key+" valor:"+valor);
				captura.setNumeroinscripciondefuncion(json.getLong("numero_inscripcion_defuncion"));
				captura.setTipoinconsistencia(1l);
				captura.setEstado(0l);
				
				listacaptura.add(captura);
			}
		}
	}
	
	void verificarfecha(String valor, String key,int posicion) {
		captura = new CapturaInconvenientes();
		Date fecha = null;
		
		if(valor!=null && valor.length()>0) {
			fecha = dateTools.fechaFormatoWs(valor);
			if(fecha==null) {
				control = true;
				captura.setCorrelativoenvio(jsonEncabezado.getLong("correlativoEnvio"));
				captura.setCui(json.getLong("cui"));
				captura.setDescripcion("Campo: "+key+" valor:"+valor);
				captura.setNumeroinscripciondefuncion(json.getLong("numero_inscripcion_defuncion"));
				captura.setTipoinconsistencia(3l);
				captura.setEstado(0l);
				
				listacaptura.add(captura);
			}
		}else {
			if(key.equals("fecha_inscripcion_defuncion")) {
				control = true;
				captura.setCorrelativoenvio(jsonEncabezado.getLong("correlativoEnvio"));
				captura.setCui(json.getLong("cui"));
				captura.setDescripcion("Campo: "+key+" valor:"+valor);
				captura.setNumeroinscripciondefuncion(json.getLong("numero_inscripcion_defuncion"));
				captura.setTipoinconsistencia(2l);
				captura.setEstado(0l);
				
				listacaptura.add(captura);
			}
		}
	}
	
	public void iniciarValidacion() throws JsonProcessingException {
		init();
		validarJson();
	}
	
}
