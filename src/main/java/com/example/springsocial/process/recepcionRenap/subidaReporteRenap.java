package com.example.springsocial.process.recepcionRenap;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.springsocial.services.ServicioArchivos;

public class subidaReporteRenap {

	private ServicioArchivos apiArchivos;
	private Logger logger=Logger.getLogger(subidaReporteRenap.class.getName());
	private String nombreCarpeta, nombreArchivo, tipo, base64;
	private boolean respuesta;
	private static String sedetxt = "SEDE_",sedeBackup="BACKUP_", datapplication="data:application/pdf;base64,",datapplicationBackup="data:text/plain;base64,", loggerstatic="REPORTE DE FALLECIDOS ENVIADO POR RENAP", loggerstaticBakcup="COPIA DE REPORTE DE FALLECIDOS ENVIADO POR RENAP COMO BACKUP";
	public boolean Response() {return respuesta;}
	
	public void parametros(String base64, String tipo ,String nombreCarpeta, String nomreArchivo) {
		this.nombreCarpeta = nombreCarpeta;
		this.nombreArchivo = nomreArchivo;
		this.tipo = tipo;
		this.base64 = base64;
	}
	
	private void init() {
		apiArchivos = new ServicioArchivos();
		respuesta = false;
	}

	private void subirReporteRenapPdf() throws Exception {
		String archivoDescargado = null, carpeta=null, dataApplication= null, textologger=null, tipoarchivo=null;
		carpeta = (tipo.equals("1"))? sedetxt:sedeBackup;
		dataApplication = (tipo.equals("1"))? datapplication:datapplicationBackup;
		textologger = (tipo.equals("1"))? loggerstatic:loggerstaticBakcup;
		tipoarchivo = (tipo.equals("1"))? "pdf":"txt";
		
		logger.log(Level.INFO,"SUBIENDO "+textologger);
		
		apiArchivos.setNombre(nombreArchivo);
		apiArchivos.setTypeFile(tipoarchivo);
		apiArchivos.setBase64(dataApplication+base64);
		apiArchivos.setFolder(carpeta+nombreCarpeta);
		apiArchivos.setTipo("reportes_fallecidos_renap_dev");
		
		apiArchivos.descargarArchivo();
		archivoDescargado = apiArchivos.getArchivoDescargado();
		
		logger.log(Level.INFO,"COMPROBANDO SI EXISTE YA EL ARCHIVO");
		logger.log(Level.INFO,"archivoDescargado: "+archivoDescargado.length());
		if(archivoDescargado!=null) {
			if(archivoDescargado.length()==0) {
				logger.log(Level.INFO,"SUBIENDO BASE64");
				apiArchivos.subidaDeArchivos();
				respuesta = true;
			}
		}
	}

	public void subirArchivo() throws Exception {
		init();
		subirReporteRenapPdf();
	}
}
