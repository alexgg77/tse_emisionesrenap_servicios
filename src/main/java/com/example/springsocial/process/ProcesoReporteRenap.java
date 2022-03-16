package com.example.springsocial.process;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import com.alibaba.fastjson.JSONObject;
import com.example.springsocial.crud.ModelSetGetTransaction;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.model.CapturaInconvenientes;
import com.example.springsocial.model.input.ReporteRenap;
import com.example.springsocial.process.recepcionRenap.GenerarBackupReporteRenap;
import com.example.springsocial.process.recepcionRenap.InsercionReporteRenap;
import com.example.springsocial.process.recepcionRenap.ValidacionReporteRenap;
import com.example.springsocial.process.recepcionRenap.subidaReporteRenap;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.DateTools;
import com.example.springsocial.tools.RestResponse;
import com.example.springsocial.tools.SearchCriteriaTools;
import com.fasterxml.jackson.core.JsonProcessingException;

@SuppressWarnings({"rawtypes","unchecked","unused"})
public class ProcesoReporteRenap {
	
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	private EntityTransaction transaction  = null;
	private EntityManager entityManager = null;
	
	private SearchCriteriaTools searchCriteriaTools= new SearchCriteriaTools();
	private ModelSetGetTransaction modelTransaction =new ModelSetGetTransaction();
	private DateTools dateTools = new DateTools();
	private ObjectSetGet data= new ObjectSetGet();
	private UserPrincipal userPrincipal =null;
	private ReporteRenap element;
	private RestResponse response;
	private Logger logger = Logger.getLogger(ProcesoReporteRenap.class.getName());
	private String token, base64Backup;
	private JSONObject respuestasPasos;
	private InsercionReporteRenap insert;
	private ValidacionReporteRenap validarjson;
	private subidaReporteRenap subirReporte;
	private GenerarBackupReporteRenap backup;
	private List<CapturaInconvenientes> listaIncovenientes;
	private List<Integer> listadoposiciones;

	public void setData(ReporteRenap createElement) {this.element = createElement;}
	public void setUserPrincipal(UserPrincipal userPrincipal) {this.userPrincipal=userPrincipal;}
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {if(entityManagerFactory!=null) this.entityManagerFactory=entityManagerFactory;}
	public void setToken(String token) {	this.token = token;}
	
	public RestResponse getResponse() {return this.response; }
	
	private void init() {
		backup = new GenerarBackupReporteRenap();
		validarjson = new ValidacionReporteRenap();
		insert = new InsercionReporteRenap();
		subirReporte = new subidaReporteRenap();
		respuestasPasos = new JSONObject();
		response = new RestResponse();
		base64Backup = null;
	}
	
	private void startTransaction() {
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.transaction = this.entityManager.getTransaction();
		this.transaction.begin();
		this.modelTransaction.setEntityManager(entityManager);
	}
	private void confirmTransactionAndSetResult() {
		transaction.commit();
	}
	
	private void validarJson() throws JsonProcessingException {
		validarjson.setData(element);
		validarjson.iniciarValidacion();
		listaIncovenientes = validarjson.getListaInconvenientes();
		listadoposiciones = validarjson.getListadoPosiciones();
	}
	
	private void insertarReporte() {
		insert.setData(element);
		insert.setListados(listaIncovenientes, listadoposiciones);
		insert.setEntityManagerFactory(entityManagerFactory);
		
		insert.iniciarInsercion();
		respuestasPasos.put("insert",insert.getResponse());
	}
	
	private void subirReporte() throws Exception {
		subirReporte.parametros(element.getReportePDF(),"1",element.getSede(),element.getCorrelativoEnvio());
		subirReporte.subirArchivo();	
		
		subirReporte.parametros(base64Backup,"2",element.getSede(),element.getCorrelativoEnvio());
		subirReporte.subirArchivo();
		
		//respuestasPasos.put("archivo",subirReporte.Response());
	}
	
	private void registrarOperacioBitacora() {
		
	}
	
	private void generarBackup() throws JsonProcessingException, FileNotFoundException {
		backup.setElement(element);
		backup.backup();
		base64Backup = backup.getBase64();
	}
	
	public void procesar(){
		try {
			init();
			validarJson();
			insertarReporte();
			generarBackup();
			subirReporte();
			registrarOperacioBitacora();
			response.setData(respuestasPasos);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
