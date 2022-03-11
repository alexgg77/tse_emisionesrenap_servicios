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
	private boolean respuesta;

	/* MODELOS */
	private twsEncabezado modeloEncabezado;
	private twsDetalle modeloDetalle;

	public void setData(Object createElement) {data.setObject(createElement);}
	public void setUserPrincipal(UserPrincipal userPrincipal) {this.userPrincipal=userPrincipal;}
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {if(entityManagerFactory!=null) this.entityManagerFactory=entityManagerFactory;}
	public void setToken(String token) {	this.token = token;}

	
	public boolean getResponse() {return this.respuesta; }
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
	}

	public void insert() throws JsonProcessingException {
		JSONObject jsonObject=data.convertAtJSONTYPE(JSONObject.class);
		modeloEncabezado.setSede(jsonObject.getLong("sede"));
		modeloEncabezado.setCorrelativoenvio(jsonObject.getLong("correlativoEnvio"));
		modeloEncabezado.setRegistros(jsonObject.getLong("registros"));
		modeloEncabezado.setRegistrador(jsonObject.getString("registrador"));
		modeloEncabezado.setFechainicio((jsonObject.getString("fechainicio")!=null && jsonObject.getString("fechainicio").length()>0)? dateTools.fechaFormatoWs(jsonObject.getString("fechainicio")):null);
		modeloEncabezado.setFechafin((jsonObject.getString("fechainicio")!=null && jsonObject.getString("fechafin").length()>0)? dateTools.fechaFormatoWs(jsonObject.getString("fechafin")):null);
		modeloEncabezado.setRutapdf(jsonObject.getString("nombreSede")+"_"+jsonObject.getString("correlativoEnvio"));
		modeloEncabezado.setFechacreacion(dateTools.get_CurrentDate());
		modeloEncabezado.setEstadoprocesado(0l);

		modelTransaction.saveWithFlush(modeloEncabezado);

		for(int i=0;i<jsonObject.getJSONArray("zfallecidos").size();i++) {
			modeloDetalle = new twsDetalle();
			JSONObject json = (JSONObject) jsonObject.getJSONArray("zfallecidos").get(i);
			modeloDetalle.setIdencabezadofalle(modeloEncabezado.getId());
			modeloDetalle.setFechacreacion(dateTools.get_CurrentDate());
			modeloDetalle.setTipo_entrega(1l);
			modeloDetalle.setFecha_defuncion((json.get("fecha_defuncion")!=null)? dateTools.fechaFormatoWs(json.getString("fecha_defuncion")):null);
			modeloDetalle.setFecha_inscripcion_defuncion((json.get("fecha_inscripcion_defuncion")!=null)? dateTools.fechaFormatoWs(json.getString("fecha_inscripcion_defuncion")):null); //NO PUEDE SER NULO
			modeloDetalle.setOrden_cedula((json.get("orden_cedula")!=null)? json.getString("orden_cedula"):null);
			modeloDetalle.setRegistro_cedula((json.get("registro_cedula")!=null)? json.getLong("registro_cedula"):null);
			modeloDetalle.setDepto_extension((json.get("depto_extension")!=null)? json.getLong("depto_extension"):null);
			modeloDetalle.setMpio_extension((json.get("munic_extension")!=null)? json.getLong("munic_extension"):null);
			modeloDetalle.setFecha_nacimiento((json.get("fecha_nacimiento")!=null)? dateTools.fechaFormatoWs(json.getString("fecha_nacimiento")):null);
			modeloDetalle.setPais_nacimiento((json.get("pais_nacimiento")!=null)? json.getLong("pais_nacimiento"):null);
			modeloDetalle.setDepto_nacimiento((json.get("depto_nacimiento")!=null)? json.getLong("depto_nacimiento"):null);
			modeloDetalle.setMpio_nacimiento((json.get("munic_nacimiento")!=null)? json.getLong("munic_nacimiento"):null);
			modeloDetalle.setGenero((json.get("genero")!=null)? json.getString("genero"):null);
			modeloDetalle.setPrimer_nombre((json.get("primer_nombre")!=null)? json.getString("primer_nombre"):null);
			modeloDetalle.setSegundo_nombre((json.get("segundo_nombre")!=null)? json.getString("segundo_nombre"):null);
			modeloDetalle.setPrimer_apellido((json.get("primer_apellido")!=null)? json.getString("primer_apellido"):null);
			modeloDetalle.setSegundo_apellido((json.get("segundo_apellido")!=null)? json.getString("segundo_apellido"):null);
			modeloDetalle.setTercer_nombre((json.get("tercer_nombre")!=null)? json.getString("tercer_nombre"):null);
			modeloDetalle.setApellido_casada((json.get("apellido_casada")!=null)? json.getString("apellido_casada"):null);
			modeloDetalle.setCui((json.get("cui")!=null)? json.getLong("cui"):null);
			modeloDetalle.setDepto_inscripcion((json.get("depto_inscripcion")!=null)? json.getLong("depto_inscripcion"):null); //NO PUEDE SER NULO
			modeloDetalle.setMpio_inscripcion((json.get("munic_inscripcion")!=null)? json.getLong("munic_inscripcion"):null); //NO PUEDE SER NULO
			modeloDetalle.setInscripcion_defuncion((json.get("numero_inscripcion_defuncion")!=null)? json.getLong("numero_inscripcion_defuncion"):null); //NO PUEDE SER NULO
			modeloDetalle.setPais_defuncion((json.get("pais_defuncion")!=null)? json.getLong("pais_defuncion"):null);
			modeloDetalle.setDepto_defuncion((json.get("depto_defuncion")!=null)? json.getLong("depto_defuncion"):null);
			modeloDetalle.setMpio_defuncion((json.get("munic_defuncion")!=null)? json.getLong("munic_defuncion"):null);			
			modeloDetalle.setCausa_defuncion((json.get("causa_defuncion")!=null)? json.getString("causa_defuncion"):null);
			modeloDetalle.setPrimer_nombre_madre((json.get("primer_nombre_madre")!=null)? json.getString("primer_nombre_madre"):null);
			modeloDetalle.setSegundo_nombre_madre((json.get("segundo_nombre_madre")!=null)? json.getString("segundo_nombre_madre"):null);
			modeloDetalle.setPrimer_apellido_madre((json.get("primer_apellido_madre")!=null)? json.getString("primer_apellido_madre"):null);
			modeloDetalle.setSegundo_apellido_madre((json.get("segundo_apellido_madre")!=null)? json.getString("segundo_apellido_madre"):null);
			modeloDetalle.setTercer_nombre_madre((json.get("tercer_nombre_madre")!=null)? json.getString("tercer_nombre_madre"):null);
			modeloDetalle.setApellido_casada_madre((json.get("apellido_casada_madre")!=null)? json.getString("apellido_casada_madre"):null);
			modeloDetalle.setPrimer_nombre_padre((json.get("primer_nombre_padre")!=null)? json.getString("primer_nombre_padre"):null);
			modeloDetalle.setSegundo_nombre_padre((json.get("segundo_nombre_padre")!=null)? json.getString("segundo_nombre_padre"):null);
			modeloDetalle.setPrimer_apellido_padre((json.get("primer_apellido_padre")!=null)? json.getString("primer_apellido_padre"):null);
			modeloDetalle.setSegundo_apellido_padre((json.get("segundo_apellido_padre")!=null)? json.getString("segundo_apellido_padre"):null);
			modeloDetalle.setTercer_nombre_padre((json.get("tercer_nombre_padre")!=null)? json.getString("tercer_nombre_padre"):null);
			modeloDetalle.setFolio_nac((json.get("folio")!=null)? json.getString("folio"):null);
			modeloDetalle.setPartida_nac((json.get("partida")!=null)? json.getString("partida"):null);
			modeloDetalle.setLibro_nac((json.get("libro")!=null)? json.getString("libro"):null);
			
			modelTransaction.saveWithFlush(modeloDetalle);
		}

	}

	public void iniciarInsercion() {
		respuesta = false;
		try {
			response= new RestResponse();
			startTransaction();
			init();
			insert();
			confirmTransactionAndSetResult();
			respuesta = true;
		}catch(Exception exception) {
			transaction.rollback();
			respuesta = false;
		}finally{
			if (entityManager.isOpen())	entityManager.close();
		}

	}
}
