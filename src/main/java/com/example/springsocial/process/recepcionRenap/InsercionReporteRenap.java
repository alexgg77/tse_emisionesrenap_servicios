package com.example.springsocial.process.recepcionRenap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import com.alibaba.fastjson.JSONObject;
import com.example.springsocial.crud.ModelSetGetTransaction;
import com.example.springsocial.crud.ObjectSetGet;
import com.example.springsocial.model.CapturaInconvenientes;
import com.example.springsocial.model.twsDetalle;
import com.example.springsocial.model.twsEncabezado;
import com.example.springsocial.model.input.fallecidos;
import com.example.springsocial.model.outputresponse.ResponseClassOperaciones;
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
	private Logger logger = Logger.getLogger(InsercionReporteRenap.class.getName());
	private SearchCriteriaTools searchCriteriaTools= new SearchCriteriaTools();
	private ModelSetGetTransaction modelTransaction =new ModelSetGetTransaction();
	private DateTools dateTools = new DateTools();
	private ObjectSetGet data= new ObjectSetGet();
	private UserPrincipal userPrincipal =null;
	private RestResponse response;
	/* MODELOS */
	private List<CapturaInconvenientes> listaIncovenientes;
	private List<Integer> listadoposiciones;
	private ArrayList<fallecidos> fallecidos;
	private twsEncabezado modeloEncabezado, modelVerificacion;
	private twsDetalle modeloDetalle;
	private CapturaInconvenientes captura;
	private ResponseClassOperaciones responseInsercion;
	/* VARIABLES */
	private String token;
	private Integer controlInserciones = null;
	private boolean respuesta, control=false,controlExiste=false;
	private Date fechainicio, fechafin;
	/* SETS DE PARAMETROS */
	public void setToken(String token) {this.token = token;}
	public void setData(Object createElement) {data.setObject(createElement);}
	public void setUserPrincipal(UserPrincipal userPrincipal) {this.userPrincipal=userPrincipal;}
	public void setListados(List<CapturaInconvenientes> listaIncovenientes,List<Integer> listadoposiciones) {this.listaIncovenientes = listaIncovenientes; this.listadoposiciones = listadoposiciones;}
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {if(entityManagerFactory!=null) this.entityManagerFactory=entityManagerFactory;}
	
	public void setParametros(ArrayList<fallecidos> fallecidos, twsEncabezado modeloVerifica) {
		this.fallecidos = fallecidos;
		this.modelVerificacion = modeloVerifica;
	}
	
	/* GETS */
	public boolean getResponse() {return this.respuesta; }
	public ResponseClassOperaciones getControlInsercion() {
		responseInsercion.setFechafin(fechafin);
		responseInsercion.setFechainicio(fechainicio);
		responseInsercion.setTotal(controlInserciones);
		return responseInsercion;
	}
	/* METODOS */
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
		responseInsercion = new ResponseClassOperaciones();
		control = false;
		controlInserciones =0;
		fechafin = null;
		fechainicio = null;
	}
	
	/*private void comprobarPosicion(Integer posicionActual) {
		for(int i=0;i<listadoposiciones.size();i++) {
			if(listadoposiciones.get(i)==posicionActual) {
				
			}
		}
	}*/
	
	private void comprobarInconveniente(JSONObject json) {
		for(int i=0;i<listaIncovenientes.size();i++) {
			logger.log(Level.INFO,"lista inconvenientes: "+listaIncovenientes.get(i).getNumeroinscripciondefuncion()+"  json: "+json.getString("numero_inscripcion_defuncion"));
			if(listaIncovenientes.get(i).getNumeroinscripciondefuncion().toString().equals(json.getString("numero_inscripcion_defuncion")) ) {
				control=true;
			}
		}
	}
	
	private void comprobarExiste(JSONObject json) {
		for(int i=0;i<fallecidos.size();i++) {
			if(json.getString("numero_inscripcion_defuncion").equals(fallecidos.get(i).getNumero_inscripcion_defuncion())) {
				controlExiste=true;
			}
		}
	}

	public void insert() throws JsonProcessingException {
		fechainicio = dateTools.get_CurrentDate();
		JSONObject jsonObject=data.convertAtJSONTYPE(JSONObject.class);
		logger.log(Level.INFO,"Definicion de modelo "+twsEncabezado.class.getName());
		modeloEncabezado.setRutapdf("SEDE_"+jsonObject.getString("sede")+","+jsonObject.getString("correlativoEnvio"));
		modeloEncabezado.setCodigodepartamento(jsonObject.getLong("departamento"));
		modeloEncabezado.setCodigomunicipio(jsonObject.getLong("municipio"));
		modeloEncabezado.setSede(jsonObject.getLong("sede"));
		//AGREGAR CAMPO NOMBRE SEDE
		modeloEncabezado.setCorrelativoenvio(jsonObject.getLong("correlativoEnvio"));
		modeloEncabezado.setRegistros(jsonObject.getLong("registros"));
		modeloEncabezado.setFechainicio((jsonObject.getString("fecha")!=null && jsonObject.getString("fecha").length()>0)? dateTools.fechaFormatoWs(jsonObject.getString("fecha")):null);
		modeloEncabezado.setFechacreacion(dateTools.get_CurrentDate());
		modeloEncabezado.setEstadoprocesado(0l);
		modeloEncabezado.setFechafin(dateTools.get_CurrentDate());
		
		//ESTOS SE ESTAN DESCARTANDO
		//modeloEncabezado.setRegistrador(jsonObject.getString("registrador"));
		
		if(modelVerificacion!=null) {
			modeloEncabezado.setId(modelVerificacion.getId());
			logger.log(Level.INFO,"Iniciando model transaction para: "+twsEncabezado.class.getName()+" UPDATE");
			modelTransaction.update(modeloEncabezado);
		}else {
			logger.log(Level.INFO,"Iniciando model transaction para: "+twsEncabezado.class.getName()+" INSERT");
			modelTransaction.saveWithFlush(modeloEncabezado);
		}

		logger.log(Level.INFO,"Iniciando insercion detalle de reporte");
		for(int i=0;i<jsonObject.getJSONArray("fallecidos").size();i++) {
			modeloDetalle = new twsDetalle();
			logger.log(Level.INFO,"Iteracion listado fallecidos: "+i);
			JSONObject json = (JSONObject) jsonObject.getJSONArray("fallecidos").get(i);
			comprobarInconveniente(json);
			if(!control) {
				comprobarExiste(json);
				if(!controlExiste) {
					controlInserciones++;
					logger.log(Level.INFO,"Definicion de modelo "+twsDetalle.class.getName());
					modeloDetalle.setIdencabezadofalle(modeloEncabezado.getId());
					modeloDetalle.setFechacreacion(dateTools.get_CurrentDate());
					modeloDetalle.setTipo_entrega(1l);
					
					modeloDetalle.setCui((json.get("cui")!=null)? json.getLong("cui"):null);
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
					modeloDetalle.setTercer_nombre((json.get("tercer_nombre")!=null)? json.getString("tercer_nombre"):null);
					modeloDetalle.setPrimer_apellido((json.get("primer_apellido")!=null)? json.getString("primer_apellido"):null);
					modeloDetalle.setSegundo_apellido((json.get("segundo_apellido")!=null)? json.getString("segundo_apellido"):null);
					modeloDetalle.setApellido_casada((json.get("apellido_casada")!=null)? json.getString("apellido_casada"):null);
					modeloDetalle.setDepto_inscripcion((json.get("depto_inscripcion")!=null)? json.getLong("depto_inscripcion"):null); //NO PUEDE SER NULO
					modeloDetalle.setMpio_inscripcion((json.get("munic_inscripcion")!=null)? json.getLong("munic_inscripcion"):null); //NO PUEDE SER NULO
					modeloDetalle.setInscripcion_defuncion((json.get("numero_inscripcion_defuncion")!=null)? json.getLong("numero_inscripcion_defuncion"):null); //NO PUEDE SER NULO
					modeloDetalle.setPais_defuncion((json.get("pais_defuncion")!=null)? json.getLong("pais_defuncion"):null);
					modeloDetalle.setDepto_defuncion((json.get("depto_defuncion")!=null)? json.getLong("depto_defuncion"):null);
					modeloDetalle.setMpio_defuncion((json.get("munic_defuncion")!=null)? json.getLong("munic_defuncion"):null);			
					modeloDetalle.setCausa_defuncion((json.get("causa_defuncion")!=null)? json.getString("causa_defuncion"):null);
					modeloDetalle.setPrimer_nombre_madre((json.get("primer_nombre_madre")!=null)? json.getString("primer_nombre_madre"):null);
					modeloDetalle.setSegundo_nombre_madre((json.get("segundo_nombre_madre")!=null)? json.getString("segundo_nombre_madre"):null);
					modeloDetalle.setTercer_nombre_madre((json.get("tercer_nombre_madre")!=null)? json.getString("tercer_nombre_madre"):null);
					modeloDetalle.setPrimer_apellido_madre((json.get("primer_apellido_madre")!=null)? json.getString("primer_apellido_madre"):null);
					modeloDetalle.setSegundo_apellido_madre((json.get("segundo_apellido_madre")!=null)? json.getString("segundo_apellido_madre"):null);
					modeloDetalle.setApellido_casada_madre((json.get("apellido_casada_madre")!=null)? json.getString("apellido_casada_madre"):null);
					modeloDetalle.setPrimer_nombre_padre((json.get("primer_nombre_padre")!=null)? json.getString("primer_nombre_padre"):null);
					modeloDetalle.setSegundo_nombre_padre((json.get("segundo_nombre_padre")!=null)? json.getString("segundo_nombre_padre"):null);
					modeloDetalle.setTercer_nombre_padre((json.get("tercer_nombre_padre")!=null)? json.getString("tercer_nombre_padre"):null);
					modeloDetalle.setPrimer_apellido_padre((json.get("primer_apellido_padre")!=null)? json.getString("primer_apellido_padre"):null);
					modeloDetalle.setSegundo_apellido_padre((json.get("segundo_apellido_padre")!=null)? json.getString("segundo_apellido_padre"):null);
					modeloDetalle.setLibro_nac((json.get("libro")!=null)? json.getString("libro"):null);
					modeloDetalle.setFolio_nac((json.get("folio")!=null)? json.getString("folio"):null);
					modeloDetalle.setPartida_nac((json.get("partida")!=null)? json.getString("partida"):null);
					
					logger.log(Level.INFO,"Iniciando model transaction para: "+twsDetalle.class.getName());
					modelTransaction.saveWithFlush(modeloDetalle);
				}else {
					controlExiste = false;
					logger.log(Level.INFO,"EN LA ITERACION: "+i+" EL CUI: "+json.get("cui")+" Y NUMERO DE INSCRIPCION DEFUNCION: "+json.get("numero_inscripcion_defuncion")+" YA SE ENCUENTRA REGISTRADO");
				}
			}else {
				control = false;
				logger.log(Level.INFO,"EN LA ITERACION: "+i+" EL CUI: "+json.get("cui")+" Y NUMERO DE INSCRIPCION DEFUNCION: "+json.get("numero_inscripcion_defuncion")+" PRESENTO INCONSISTENCIAS AL INTENTAR INSERTARLO");
			}
		}
		fechafin = dateTools.get_CurrentDate();
	}
	
	public void inconsistencias() {
		for(int i=0;i<listaIncovenientes.size();i++) {
			captura = new CapturaInconvenientes();
			captura = listaIncovenientes.get(i);
			modelTransaction.saveWithFlush(captura);
		}
	}

	public void iniciarInsercion() {
		respuesta = false;
		try {
			response= new RestResponse();
			startTransaction();
			init();
			insert();
			inconsistencias();
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