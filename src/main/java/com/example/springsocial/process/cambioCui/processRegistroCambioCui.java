package com.example.springsocial.process.cambioCui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import com.example.springsocial.crud.ModelSetGetTransaction;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.error.CustomException;
import com.example.springsocial.model.db.modelBdCambioCui;
import com.example.springsocial.model.db.modelBdId;
import com.example.springsocial.model.input.modelCambioCui;
import com.example.springsocial.tools.DateTools;
import com.example.springsocial.tools.RestResponse;
import com.example.springsocial.tools.SearchCriteriaTools;

@SuppressWarnings({"rawtypes","unchecked","unused"})
public class processRegistroCambioCui {

	private EntityManagerFactory entityManagerFactory;
	private EntityTransaction transaction  = null;
	private EntityManager entityManager = null;
	
	private SearchCriteriaTools searchCriteriaTools= new SearchCriteriaTools();
	private ModelSetGetTransaction modelTransaction =new ModelSetGetTransaction();
	private DateTools dateTools = new DateTools();
	private ObjectSetGet data= new ObjectSetGet();
	
	/* MODELOS */
	private modelCambioCui element;
	private modelBdCambioCui modeloCambio, modeloVerificaCui;
	private List<modelBdId> modeloId;
	
	/* VARIABLES */
	private RestResponse response;
	private Long id;
	
	/* Logger */
	private Logger logger = Logger.getLogger(processRegistroCambioCui.class.getName());
	
	public void setData(modelCambioCui element) {this.element = element;}
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {if(entityManagerFactory!=null) this.entityManagerFactory=entityManagerFactory;}
	
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
	
	private void init() {
		modeloCambio = new modelBdCambioCui();
		modeloVerificaCui = new modelBdCambioCui();
		modeloId = new ArrayList<modelBdId>();
		response = new RestResponse();
		id = null;
	}
	
	private void verificaCui() throws CustomException {
		logger.log(Level.INFO,"INICIANDO VERIFICACION DEL CUI: "+element.getCui()+" Y NUMERO DE SOLICITUD: "+element.getNumerosolicitud());
		modelTransaction.setType(modelBdCambioCui.class);
		searchCriteriaTools.clear();
		searchCriteriaTools.addIgualAnd("cui",element.getCui());
		searchCriteriaTools.addIgualAnd("cui_anterior",element.getCuianterior());
		searchCriteriaTools.addIgualAnd("solicitud_renap",element.getNumerosolicitud());
		modelTransaction.setSearchCriteriaTools(searchCriteriaTools);
		modelTransaction.getValue();
		this.modeloVerificaCui = (modelBdCambioCui) modelTransaction.getResult();
	}
	
	private void obtenerid() {
		String queryExecute ="select MAX(idcambio_cui)+1 as id FROM tcambio_cui";
		modeloId = this.entityManager.createNativeQuery(queryExecute,modelBdId.class).getResultList();
		
		if(modeloId.get(0)!=null && !modeloId.isEmpty()) {
			id = Long.valueOf(modeloId.get(0).getId());
		}else {
			id = 1l;
		}
	}
	
	private void registrarCui() {
		if(modeloVerificaCui==null) {
			logger.log(Level.INFO,"INICIANDO REGISTRO DEL CAMBIO DE CUI: "+element.getCui());
			modeloCambio.setId(id);
			modeloCambio.setCui(Long.valueOf(element.getCui()));
			modeloCambio.setCuianterior(Long.valueOf(element.getCuianterior()));
			modeloCambio.setNumerosolicitud(element.getNumerosolicitud());
			modeloCambio.setSerie(Long.valueOf(element.getSerie()));
			modeloCambio.setFecha(dateTools.get_CurrentDate());
						
			modelTransaction.saveWithFlush(modeloCambio);
			
			response.setData("CUI: "+element.getCui()+" REGISTRADRO CORRECTAMENTE");
		}else {
			response.setError("EL CUI: "+element.getCui()+" Y NUMERO DE SOLICITUD: "+element.getNumerosolicitud()+" YA SE ENCUENTRA REGISTRADO");
			logger.log(Level.INFO,"EL CUI: "+element.getCui()+" Y NUMERO DE SOLICITUD: "+element.getNumerosolicitud()+" YA SE ENCUENTRA REGISTRADO");
		}
	}
	
	
	public void iniciarCambioCui() {
		try {
			init();
			startTransaction();
			verificaCui();
			obtenerid();
			registrarCui();
			confirmTransactionAndSetResult();
		} catch (CustomException e) {
			response.setError(e.getCause());
		}finally {
			if (entityManager.isOpen())	entityManager.close();
		}
	}
	
	
}
