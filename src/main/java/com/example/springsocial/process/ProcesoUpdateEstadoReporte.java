package com.example.springsocial.process;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import com.example.springsocial.crud.ModelSetGetTransaction;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.error.CustomException;
import com.example.springsocial.model.twsDetalle;
import com.example.springsocial.model.twsEncabezado;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.DateTools;
import com.example.springsocial.tools.SearchCriteriaTools;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class ProcesoUpdateEstadoReporte {

	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	private EntityTransaction transaction  = null;
	private EntityManager entityManager = null;
	
	private SearchCriteriaTools searchCriteriaTools= new SearchCriteriaTools();
	private ModelSetGetTransaction modelTransaction =new ModelSetGetTransaction();
	private DateTools dateTools = new DateTools();
	private ObjectSetGet data= new ObjectSetGet();
	private UserPrincipal userPrincipal =null;	
	private Logger logger = Logger.getLogger(ProcesoUpdateEstadoReporte.class.getName());
	private String token;
	private twsEncabezado encabezadoModel, encabezadoUpdate;
	
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
	
	private void obtenerDatos() throws Exception, CustomException {
		modelTransaction.setType(twsEncabezado.class);
		searchCriteriaTools.clear();
		searchCriteriaTools.addIgualAnd("id", data.getValue("id").toString()); //no. folio
		modelTransaction.setSearchCriteriaTools(searchCriteriaTools);
		modelTransaction.getValue();
		this.encabezadoModel  =  (twsEncabezado) modelTransaction.getResult();
	}
	
	private void update() {
		encabezadoUpdate = new twsEncabezado();
		encabezadoUpdate.setCorrelativoenvio(encabezadoModel.getCorrelativoenvio());
		encabezadoUpdate.setEstadoprocesado(1l);
		encabezadoUpdate.setFechacreacion(encabezadoModel.getFechacreacion());
		encabezadoUpdate.setFechafin(encabezadoModel.getFechafin());
		encabezadoUpdate.setFechainicio(encabezadoModel.getFechainicio());
		encabezadoUpdate.setId(encabezadoModel.getId());
		encabezadoUpdate.setRegistrador(encabezadoModel.getRegistrador());
		encabezadoUpdate.setRegistros(encabezadoModel.getRegistros());
		encabezadoUpdate.setRutapdf(encabezadoModel.getRutapdf());
		encabezadoUpdate.setSede(encabezadoModel.getSede());
		
		modelTransaction.update(encabezadoUpdate);
	}
	
	public void iniciarUpdate() {
		try {
			startTransaction();
			obtenerDatos();
			update();
			confirmTransactionAndSetResult();
		} catch (Exception e) {
			e.printStackTrace();
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}
	
	
}
