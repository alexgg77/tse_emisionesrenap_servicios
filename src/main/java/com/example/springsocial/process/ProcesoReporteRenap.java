package com.example.springsocial.process;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import com.example.springsocial.crud.ModelSetGetTransaction;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.model.input.ReporteRenap;
import com.example.springsocial.process.recepcionRenap.InsercionReporteRenap;
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
	private String token;
	
	private InsercionReporteRenap insert = new InsercionReporteRenap();

	public void setData(ReporteRenap createElement) {this.element = createElement;}
	public void setUserPrincipal(UserPrincipal userPrincipal) {this.userPrincipal=userPrincipal;}
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {if(entityManagerFactory!=null) this.entityManagerFactory=entityManagerFactory;}
	public void setToken(String token) {	this.token = token;}
	
	public RestResponse getResponse() {return this.response; }
	
	private void startTransaction() {
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.transaction = this.entityManager.getTransaction();
		this.transaction.begin();
		this.modelTransaction.setEntityManager(entityManager);
	}
	private void confirmTransactionAndSetResult() {
		transaction.commit();
	}
	
	public void procesar() throws JsonProcessingException {
		insert.setToken(token);
		insert.setEntityManagerFactory(entityManagerFactory);
		insert.setData(element);
		insert.insert();
	}
	
}
