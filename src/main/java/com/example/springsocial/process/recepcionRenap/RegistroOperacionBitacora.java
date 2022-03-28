package com.example.springsocial.process.recepcionRenap;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import com.example.springsocial.crud.ModelSetGetTransaction;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.model.CapturaInconvenientes;
import com.example.springsocial.model.twsBitacora;
import com.example.springsocial.model.twsEncabezado;
import com.example.springsocial.model.input.ReporteRenap;
import com.example.springsocial.model.outputresponse.ResponseClassOperaciones;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.tools.DateTools;
import com.example.springsocial.tools.RestResponse;
import com.example.springsocial.tools.SearchCriteriaTools;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class RegistroOperacionBitacora {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	private EntityTransaction transaction  = null;
	private EntityManager entityManager = null;
	private Logger logger = Logger.getLogger(RegistroOperacionBitacora.class.getName());
	private SearchCriteriaTools searchCriteriaTools= new SearchCriteriaTools();
	private ModelSetGetTransaction modelTransaction =new ModelSetGetTransaction();
	private DateTools dateTools = new DateTools();
	private ObjectSetGet data= new ObjectSetGet();
	private UserPrincipal userPrincipal =null;
	private RestResponse response;
	private ReporteRenap element;
	private ResponseClassOperaciones responseBackup, responseInsercion;
	private twsBitacora modeloBitacora;
	private twsEncabezado modeloEncabezado;
	private String token;
	private Integer totalRecibidos, totalInconvenientes;
	
	public void setData(ReporteRenap createElement) {this.element = createElement;}
	public void setToken(String token) {this.token = token;}
	public void setUserPrincipal(UserPrincipal userPrincipal) {this.userPrincipal=userPrincipal;}
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {if(entityManagerFactory!=null) this.entityManagerFactory=entityManagerFactory;}
	
	
	public void setParametros(ResponseClassOperaciones responseBackup,ResponseClassOperaciones responseInsercion,Integer totalRecibidos,Integer totalInconvenientes,twsEncabezado modeloEncabezado) {
		this.responseBackup = responseBackup;
		this.responseInsercion = responseInsercion;
		this.totalInconvenientes = totalInconvenientes;
		this.totalRecibidos = totalRecibidos;
		this.modeloEncabezado = modeloEncabezado;
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
	
	private void init() {
		modeloBitacora = new twsBitacora();
	}
	
	private void registrarOperacion() {
		modeloBitacora.setCorrelativoenvio(Long.valueOf(element.getCorrelativoEnvio()));
		modeloBitacora.setEstado((totalInconvenientes>0)? 1l:0l);
		modeloBitacora.setFechacreacion(dateTools.get_CurrentDate());
		
		modeloBitacora.setFechafinbackup(responseBackup.getFechafin());
		modeloBitacora.setFechafinregistrodata(responseInsercion.getFechafin());
		modeloBitacora.setFechainiciobackup(responseBackup.getFechainicio());
		modeloBitacora.setFechainicioregistrodata(responseInsercion.getFechainicio());
		modeloBitacora.setTipoentrega((modeloEncabezado!=null)? 2l:1l);
		modeloBitacora.setTotalregistrosbackup(Long.valueOf(responseBackup.getTotal().toString()));
		modeloBitacora.setTotalregistrosinsertados(Long.valueOf(responseInsercion.getTotal().toString()));
		modeloBitacora.setTotalregistrosnoinsertados(Long.valueOf(totalInconvenientes.toString()));
		modeloBitacora.setTotalregistrosrecibidos(Long.valueOf(totalRecibidos.toString()));
		
		modelTransaction.saveWithFlush(modeloBitacora);
	}
	
	public void iniciarBitacora() {
		init();
		startTransaction();
		registrarOperacion();
		confirmTransactionAndSetResult();
	}
}