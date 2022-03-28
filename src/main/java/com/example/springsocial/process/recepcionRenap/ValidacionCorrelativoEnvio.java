package com.example.springsocial.process.recepcionRenap;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
import com.example.springsocial.model.input.ReporteRenap;
import com.example.springsocial.model.input.fallecidos;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.DateTools;
import com.example.springsocial.tools.RestResponse;
import com.example.springsocial.tools.SearchCriteriaTools;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class ValidacionCorrelativoEnvio {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	private EntityTransaction transaction  = null;
	private EntityManager entityManager = null;
	private Logger logger = Logger.getLogger(ValidacionCorrelativoEnvio.class.getName());
	private SearchCriteriaTools searchCriteriaTools= new SearchCriteriaTools();
	private ModelSetGetTransaction modelTransaction =new ModelSetGetTransaction();
	private DateTools dateTools = new DateTools();
	private ObjectSetGet data= new ObjectSetGet();
	private UserPrincipal userPrincipal =null;
	private RestResponse response;
	private twsEncabezado modeloEncabezado;
	private twsDetalle modeloDetalle;
	private ReporteRenap element, nuevoElemento;
	private ArrayList<fallecidos> fallecidos; //FALLECIDOS YA REGISTRADOS CON ESTE MISMO CORRELATIVO ENVIO
	
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {if(entityManagerFactory!=null) this.entityManagerFactory=entityManagerFactory;}
	public void setData(ReporteRenap createElement) {this.element = createElement;}
	
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
		modeloEncabezado = new twsEncabezado();
		modeloDetalle = new twsDetalle();
		fallecidos = new ArrayList<fallecidos>();
	}
	
	public ArrayList<fallecidos> getFallecidos(){
		return fallecidos;
	}
	
	public twsEncabezado getModelo() {
		return modeloEncabezado;
	}
	
	private void correlativoEnvio() throws Exception, CustomException {
		modelTransaction.setType(twsEncabezado.class);
		searchCriteriaTools.clear();
		searchCriteriaTools.addIgualAnd("correlativoenvio", element.getCorrelativoEnvio());
		modelTransaction.setSearchCriteriaTools(searchCriteriaTools);
		modelTransaction.getValue();
		this.modeloEncabezado = (twsEncabezado) modelTransaction.getResult();
	}
	
	private void validarDetalle() throws CustomException {
		if(modeloEncabezado!=null) {
			for(int i=0;i<element.getFallecidos().size();i++) {
				logger.log(Level.INFO,"NUMERO DE INCRIPCION DEFUNCION: "+element.getFallecidos().get(i).getNumero_inscripcion_defuncion() +" ID ENCABEZADO: "+modeloEncabezado.getId());
				modeloDetalle = new twsDetalle();
				modelTransaction.setType(twsDetalle.class);
				searchCriteriaTools.clear();
				searchCriteriaTools.addIgualAnd("inscripcion_defuncion",element.getFallecidos().get(i).getNumero_inscripcion_defuncion());
				searchCriteriaTools.addIgualAnd("idencabezadofalle",modeloEncabezado.getId().toString());
				modelTransaction.setSearchCriteriaTools(searchCriteriaTools);
				modelTransaction.getValue();
				this.modeloDetalle = (twsDetalle) modelTransaction.getResult();
				
				if(modeloDetalle!=null) {
					fallecidos.add(element.getFallecidos().get(i));
				}
			}
		}
	}
	
	public void iniciarValidacion() throws Exception, CustomException {		
		try {
			init();
			startTransaction();
			correlativoEnvio();
			validarDetalle();
			confirmTransactionAndSetResult();
		}catch(Exception exception) {
			transaction.rollback();
		}finally{
			if (entityManager.isOpen())	entityManager.close();
		}
	}
}