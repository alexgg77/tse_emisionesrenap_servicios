package com.example.springsocial.process.recepcionRenap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONObject;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.model.CapturaInconvenientes;
import com.example.springsocial.model.input.ReporteRenap;
import com.example.springsocial.model.outputresponse.ResponseClassOperaciones;
import com.example.springsocial.tools.DateTools;
import com.fasterxml.jackson.core.JsonProcessingException;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class GenerarBackupReporteRenap {
	
	private Logger logger = Logger.getLogger(GenerarBackupReporteRenap.class.getName());
	private ReporteRenap elementrecibido;
	private DateTools dateTools = new DateTools();
	private ObjectSetGet data= new ObjectSetGet();
	private JSONObject json, jsonEncabezado;
	private String newRuta, encodedFile;
	private File file;
	private PrintWriter escribir;
	private Base64 base64;
	private InputStream inputStream;
	private Integer totalRegistrosBackup;
	private Date fechainicio, fechafin;
	private ResponseClassOperaciones responseResultados;
	
	public void setData(ReporteRenap element) {data.setObject(element);}
	public void setElement(ReporteRenap element) {this.elementrecibido = element;}
	
	private void init() throws JsonProcessingException {
		this.totalRegistrosBackup = 0;
		this.fechafin = null;
		this.fechainicio = null;
		this.inputStream = null;
		this.encodedFile = null;
		this.escribir = null;
		this.file = null;
		this.newRuta = null;
		this.base64 = new Base64();
		this.jsonEncabezado=data.convertAtJSONTYPE(JSONObject.class);
		this.responseResultados = new ResponseClassOperaciones();
	}
	
	public ResponseClassOperaciones getResultados() {
		responseResultados.setBase64(encodedFile);
		responseResultados.setFechafin(fechafin);
		responseResultados.setFechainicio(fechainicio);
		responseResultados.setTotal(totalRegistrosBackup);
		return responseResultados;
	}
	
	private void ruta(String documento) {
		fechainicio = dateTools.get_CurrentDate();
		try{
			String current = new java.io.File( "." ).getAbsolutePath();
	        System.out.println("Current dir:"+current);
	        
	        ClassLoader classLoader = getClass().getClassLoader();
	        File file = new File(classLoader.getResource(documento).getFile());
	        newRuta=file.getCanonicalPath();
	        System.out.println(newRuta);
	        newRuta= "target/classes/"+documento;
	        if(file.exists()) {System.out.println("Se creo el archivo");}
	        else {System.out.println("No se creo el archivo");}
	        System.out.println("new path: "+newRuta);
		}catch(Exception ex) {
			ex.printStackTrace();
		}		
	}
	
	@SuppressWarnings("static-access")
	public void base64() {
		file = new File(newRuta);
		byte[] fileArray = new byte[(int) file.length()];
		try {
			inputStream = new FileInputStream(file);
			inputStream.read(fileArray);
			encodedFile =base64.encodeBase64String(fileArray);
		} catch (Exception e) {
			e.getMessage();
		}
		fechafin = dateTools.get_CurrentDate();
		totalRegistrosBackup = elementrecibido.getFallecidos().size();
	}
	
	private void escribirBackup() throws FileNotFoundException {
		file = new File(newRuta);
		escribir = new PrintWriter(file);
		escribir.write("DATOS ENCABEZADO \n");
		escribir.write(elementrecibido.toString()+"\n");
		escribir.write("DATOS DETALLE REPORTE \n");
		escribir.write(elementrecibido.getFallecidos().toString());
		
		escribir.flush();
		escribir.close();
		
	}
	
	public void backup() throws JsonProcessingException, FileNotFoundException {
		init();
		ruta("backup.txt");
		escribirBackup();
		base64();
	}
	
	
}
