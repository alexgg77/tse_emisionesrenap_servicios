package com.example.springsocial.process.recepcionRenap;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.springsocial.services.ServicioArchivos;

public class subidaReporteRenap {

	private ServicioArchivos apiArchivos;
	private Logger logger=Logger.getLogger(subidaReporteRenap.class.getName());
	private String sede, correlativoEnvio;
	private boolean respuesta;
	private static String sedetxt = "SEDE_";
	public boolean Response() {return respuesta;}
	
	public void parametros(String sede, String correlativoEnvio) {
		this.sede = sede;
		this.correlativoEnvio= correlativoEnvio;
	}
	
	private void init() {
		apiArchivos = new ServicioArchivos();
		respuesta = false;
	}

	private void subirReporteRenapPdf(String base64) throws Exception {
		logger.log(Level.INFO,"SUBIENDO REPORTE DE FALLECIDOS ENVIADO POR RENAP");
		String archivoDescargado = null, respuestaSubida=null;
		apiArchivos.setNombre(correlativoEnvio);
		apiArchivos.setTypeFile("pdf");
		apiArchivos.setBase64("data:application/pdf;base64,"+base64);
		apiArchivos.setFolder(sedetxt+sede);
		apiArchivos.setTipo("reportes_fallecidos_renap_dev");
							 
		apiArchivos.descargarArchivo();
		archivoDescargado = apiArchivos.getArchivoDescargado();
		
		if(archivoDescargado.length()==0) {
			logger.log(Level.INFO,"SUBIENDO BASE64");
			apiArchivos.subidaDeArchivos();
			respuesta = true;
		}
	}

	public void subirArchivo(String base64) throws Exception {
		init();
		subirReporteRenapPdf(base64);
	}
}
