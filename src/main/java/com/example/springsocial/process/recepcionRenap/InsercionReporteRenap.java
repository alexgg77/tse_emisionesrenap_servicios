package com.example.springsocial.process.recepcionRenap;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import com.alibaba.fastjson.JSONObject;
import com.example.springsocial.crud.ModelSetGetTransaction;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.model.twsDetalle;
import com.example.springsocial.model.twsEncabezado;
import com.example.springsocial.process.ProcesoReporteRenap;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.DateTools;
import com.example.springsocial.tools.RestResponse;
import com.example.springsocial.tools.SearchCriteriaTools;
import com.fasterxml.jackson.core.JsonProcessingException;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class InsercionReporteRenap {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	private EntityTransaction transaction  = null;
	private EntityManager entityManager = null;

	private SearchCriteriaTools searchCriteriaTools= new SearchCriteriaTools();
	private ModelSetGetTransaction modelTransaction =new ModelSetGetTransaction();
	private DateTools dateTools = new DateTools();
	private ObjectSetGet data= new ObjectSetGet();
	private UserPrincipal userPrincipal =null;
	private RestResponse response;
	private Logger logger = Logger.getLogger(InsercionReporteRenap.class.getName());
	private String token;

	/* MODELOS */
	private twsEncabezado modeloEncabezado;
	private twsDetalle modeloDetalle;

	public void setData(Object createElement) {data.setObject(createElement);}
	public void setUserPrincipal(UserPrincipal userPrincipal) {this.userPrincipal=userPrincipal;}
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {if(entityManagerFactory!=null) this.entityManagerFactory=entityManagerFactory;}
	public void setToken(String token) {	this.token = token;}

	private void startTransaction() {
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.transaction = this.entityManager.getTransaction();
		this.transaction.begin();
		this.modelTransaction.setEntityManager(entityManager);
	}
	private void confirmTransactionAndSetResult() {
		transaction.commit();
	}

	public void insert() throws JsonProcessingException {
		modeloDetalle = new twsDetalle(); modeloEncabezado = new twsEncabezado();
		JSONObject jsonObject=data.convertAtJSONTYPE(JSONObject.class);

		modeloEncabezado.setSede(jsonObject.getLong("sede"));
		modeloEncabezado.setCorrelativoenvio(jsonObject.getLong("correlativoenvio"));
		modeloEncabezado.setRegistros(jsonObject.getLong("registros"));
		modeloEncabezado.setRegistrador(jsonObject.getString("registrador"));
		modeloEncabezado.setFechainicio((jsonObject.getString("fechainicio")!=null && jsonObject.getString("fechainicio").length()>0)? dateTools.fechaFormatoWs(jsonObject.getString("fechainicio")):null);
		modeloEncabezado.setFechafin((jsonObject.getString("fechainicio")!=null && jsonObject.getString("fechafin").length()>0)? dateTools.fechaFormatoWs(jsonObject.getString("fechafin")):null);
		modeloEncabezado.setRutapdf(token);
		modeloEncabezado.setFechacreacion(dateTools.get_CurrentDate());
		modeloEncabezado.setEstadoprocesado(0l);

		modelTransaction.saveWithFlush(modeloEncabezado);

		for(int i=0;i<jsonObject.size();i++) {
			JSONObject json = (JSONObject) jsonObject.getJSONArray("zfallecidos").get(i);

		}

	}

	public void iniciarInsercion() {
		try {
			response= new RestResponse();
			startTransaction();
			insert();
			confirmTransactionAndSetResult();
			
			response.setData("REPORTE INGRESADO CORRECTAMENTE");
		}catch(Exception exception) {
			transaction.rollback();
			response.setError(exception.getMessage());
		}finally{
			if (entityManager.isOpen())	entityManager.close();
		}

	}
}
