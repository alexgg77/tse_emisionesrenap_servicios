package com.example.springsocial.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.springsocial.api.ApiFiles;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.tools.RestResponse;

public class ServicioArchivos {
	private ApiFiles files;
	private String nombre, tipo, folder, base64, archivoDescargado,typeFile;
	private ObjectSetGet respuestaApiDeArchivos = new ObjectSetGet();
	private Logger logger=Logger.getLogger(ServicioArchivos.class.getName());
	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setTipo(String tipo) {this.tipo = tipo;}
	public void setTypeFile(String typeFile) {this.typeFile=typeFile;}
	public void setFolder(String folder) {this.folder = folder;}
	public void setBase64(String base64) {this.base64 = base64;}
	public String getArchivoDescargado() {return archivoDescargado;}
	
	
	
	public void subidaDeArchivos() throws Exception {
		logger.log(Level.INFO,"Asignando parametros para subir el archivo: "+nombre+" ,"+folder);
		files = new ApiFiles();
		files.clearParms();
		files.setPostPath();
		files.addParam("name", nombre);
		files.addParam("base64", base64);
		files.addParam("folder", folder);
		files.addParam("type", tipo);
		files.sendPost();
		if (files.getRestResponse().getError() != null) throw new Exception("No se puede obtener el archivo:" + nombre);
	}

	private void descargaDeArchivos() throws Exception {
		logger.log(Level.INFO,"Descargando el archivo: "+nombre+" ,"+folder);
		files = new ApiFiles();
		files.clearParms();		
		if(this.typeFile!=null)	files.setGetPath(tipo, folder, nombre+"."+typeFile);
		else files.setGetPath(tipo, folder, nombre);
		files.sendGet();
		if (files.getRestResponse().getError() != null)
			throw new Exception("No se puede obtener el archivo:" + nombre);
	}
	
	private void obtenerArchivoDescargado() throws Exception {
		logger.log(Level.INFO,"Obteniendo el archivo: "+nombre+" ,"+folder);
		if(files.getRestResponse().getData()!=null) {
		respuestaApiDeArchivos.setObject(files.getRestResponse().getData());		
		this.archivoDescargado = respuestaApiDeArchivos.getValue("base64").toString();
		}else {			
		  this.archivoDescargado = null;
		}
	}
	public RestResponse getResponse() {
		return this.files.getRestResponse();
	}

	public void descargarArchivo() throws Exception{
		descargaDeArchivos();
		obtenerArchivoDescargado();
	}
}
