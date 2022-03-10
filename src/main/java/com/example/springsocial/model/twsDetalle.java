package com.example.springsocial.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="TWS_DETALLEFALLE")
@SequenceGenerator(name = "default_gen", sequenceName = "SEQ_IDDETALLEFALLE", allocationSize = 1)
public class twsDetalle implements Serializable{
	
	@GeneratedValue(generator="default_gen")
	@Id
	@Column(name="iddetallefalle")
	private Long  iddetallefalle; 
	@Column(name="idencabezadofalle")
	private Long idencabezadofalle;     
	@Column(name="fechacreacion")
	private Date fechacreacion;           
	@Column(name="tipo_entrega")
	private Long tipo_entrega;      
	@Column(name="fecha_defuncion")
	private Date fecha_defuncion; 
	@Column(name="fecha_inscripcion_defuncion")
	private Date fecha_inscripcion_defuncion;       
	@Column(name="orden_cedula")
	private String orden_cedula;  
	@Column(name="registro_cedula")
	private Long registro_cedula; 
	@Column(name="depto_extension")
	private Long depto_extension;      
	@Column(name="mpio_extension")
	private Long mpio_extension;  
	@Column(name="fecha_nacimiento")
	private Date fecha_nacimiento; //arreglar con carlos motta   
	@Column(name="pais_nacimiento")
	private Long pais_nacimiento; 
	@Column(name="depto_nacimiento")
	private Long depto_nacimiento;
	@Column(name="mpio_nacimiento")
	private Long mpio_nacimiento; 
	@Column(name="genero")
	private String genero; //arreglar con carlos motta    
	@Column(name="primer_nombre")
 	private String primer_nombre; 
	@Column(name="segundo_nombre")
	private String segundo_nombre;
	@Column(name="primer_apellido")
	private String primer_apellido;
	@Column(name="tercer_nombre")
	private String tercer_nombre;
	@Column(name="segundo_apellido")
	private String segundo_apellido;
	@Column(name="apellido_casada")
	private String apellido_casada; 
	@Column(name="cui")
	private Long cui; 
	@Column(name="depto_inscripcion")
	private Long depto_inscripcion;
	@Column(name="mpio_inscripcion")
	private Long mpio_inscripcion;
	@Column(name="inscripcion_defuncion")
	private Long inscripcion_defuncion;   
	@Column(name="pais_defuncion")
	private Long pais_defuncion;  
	@Column(name="depto_defuncion")
	private Long depto_defuncion; 
	@Column(name="mpio_defuncion")
	private Long mpio_defuncion;     
	@Column(name="causa_defuncion")
	private String causa_defuncion;
	@Column(name="primer_nombre_madre")
	private String primer_nombre_madre; 
	@Column(name="segundo_nombre_madre")
	private String segundo_nombre_madre;
	@Column(name="primer_apellido_madre")
	private String primer_apellido_madre;  
	@Column(name="tercer_nombre_madre")
	private String tercer_nombre_madre;
	@Column(name="segundo_apellido_madre")
	private String segundo_apellido_madre; 
	@Column(name="apellido_casada_madre")
	private String apellido_casada_madre;
	@Column(name="primer_nombre_padre")
	private String primer_nombre_padre;  
	@Column(name="segundo_nombre_padre")
	private String segundo_nombre_padre;  
	@Column(name="primer_apellido_padre")
	private String primer_apellido_padre;  
	@Column(name="tercer_nombre_padre")
	private String tercer_nombre_padre;   
	@Column(name="segundo_apellido_padre")
	private String segundo_apellido_padre; 
	@Column(name="folio_nac")
	private String folio_nac;  
	@Column(name="partida_nac")
	private String partida_nac;   
	@Column(name="libro_nac")
	private String libro_nac;
	
	
	
	public twsDetalle() {
	}
	public twsDetalle(Long iddetallefalle, Long idencabezadofalle, Date fechacreacion, Long tipo_entrega,
			Date fecha_defuncion, Date fecha_inscripcion_defuncion, String orden_cedula, Long registro_cedula,
			Long depto_extension, Long mpio_extension, Date fecha_nacimiento, Long pais_nacimiento,
			Long depto_nacimiento, Long mpio_nacimiento, String genero, String primer_nombre, String segundo_nombre,
			String primer_apellido, String tercer_nombre, String segundo_apellido, String apellido_casada, Long cui,
			Long depto_inscripcion, Long mpio_inscripcion, Long inscripcion_defuncion, Long pais_defuncion,
			Long depto_defuncion, Long mpio_defuncion, String causa_defuncion, String primer_nombre_madre,
			String segundo_nombre_madre, String primer_apellido_madre, String tercer_nombre_madre,
			String segundo_apellido_madre, String apellido_casada_madre, String primer_nombre_padre,
			String segundo_nombre_padre, String primer_apellido_padre, String tercer_nombre_padre,
			String segundo_apellido_padre, String folio_nac, String partida_nac, String libro_nac) {
		this.iddetallefalle = iddetallefalle;
		this.idencabezadofalle = idencabezadofalle;
		this.fechacreacion = fechacreacion;
		this.tipo_entrega = tipo_entrega;
		this.fecha_defuncion = fecha_defuncion;
		this.fecha_inscripcion_defuncion = fecha_inscripcion_defuncion;
		this.orden_cedula = orden_cedula;
		this.registro_cedula = registro_cedula;
		this.depto_extension = depto_extension;
		this.mpio_extension = mpio_extension;
		this.fecha_nacimiento = fecha_nacimiento;
		this.pais_nacimiento = pais_nacimiento;
		this.depto_nacimiento = depto_nacimiento;
		this.mpio_nacimiento = mpio_nacimiento;
		this.genero = genero;
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.primer_apellido = primer_apellido;
		this.tercer_nombre = tercer_nombre;
		this.segundo_apellido = segundo_apellido;
		this.apellido_casada = apellido_casada;
		this.cui = cui;
		this.depto_inscripcion = depto_inscripcion;
		this.mpio_inscripcion = mpio_inscripcion;
		this.inscripcion_defuncion = inscripcion_defuncion;
		this.pais_defuncion = pais_defuncion;
		this.depto_defuncion = depto_defuncion;
		this.mpio_defuncion = mpio_defuncion;
		this.causa_defuncion = causa_defuncion;
		this.primer_nombre_madre = primer_nombre_madre;
		this.segundo_nombre_madre = segundo_nombre_madre;
		this.primer_apellido_madre = primer_apellido_madre;
		this.tercer_nombre_madre = tercer_nombre_madre;
		this.segundo_apellido_madre = segundo_apellido_madre;
		this.apellido_casada_madre = apellido_casada_madre;
		this.primer_nombre_padre = primer_nombre_padre;
		this.segundo_nombre_padre = segundo_nombre_padre;
		this.primer_apellido_padre = primer_apellido_padre;
		this.tercer_nombre_padre = tercer_nombre_padre;
		this.segundo_apellido_padre = segundo_apellido_padre;
		this.folio_nac = folio_nac;
		this.partida_nac = partida_nac;
		this.libro_nac = libro_nac;
	}
	public Long getIddetallefalle() {
		return iddetallefalle;
	}
	public Long getIdencabezadofalle() {
		return idencabezadofalle;
	}
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public Long getTipo_entrega() {
		return tipo_entrega;
	}
	public Date getFecha_defuncion() {
		return fecha_defuncion;
	}
	public Date getFecha_inscripcion_defuncion() {
		return fecha_inscripcion_defuncion;
	}
	public String getOrden_cedula() {
		return orden_cedula;
	}
	public Long getRegistro_cedula() {
		return registro_cedula;
	}
	public Long getDepto_extension() {
		return depto_extension;
	}
	public Long getMpio_extension() {
		return mpio_extension;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public Long getPais_nacimiento() {
		return pais_nacimiento;
	}
	public Long getDepto_nacimiento() {
		return depto_nacimiento;
	}
	public Long getMpio_nacimiento() {
		return mpio_nacimiento;
	}
	public String getGenero() {
		return genero;
	}
	public String getPrimer_nombre() {
		return primer_nombre;
	}
	public String getSegundo_nombre() {
		return segundo_nombre;
	}
	public String getPrimer_apellido() {
		return primer_apellido;
	}
	public String getTercer_nombre() {
		return tercer_nombre;
	}
	public String getSegundo_apellido() {
		return segundo_apellido;
	}
	public String getApellido_casada() {
		return apellido_casada;
	}
	public Long getCui() {
		return cui;
	}
	public Long getDepto_inscripcion() {
		return depto_inscripcion;
	}
	public Long getMpio_inscripcion() {
		return mpio_inscripcion;
	}
	public Long getInscripcion_defuncion() {
		return inscripcion_defuncion;
	}
	public Long getPais_defuncion() {
		return pais_defuncion;
	}
	public Long getDepto_defuncion() {
		return depto_defuncion;
	}
	public Long getMpio_defuncion() {
		return mpio_defuncion;
	}
	public String getCausa_defuncion() {
		return causa_defuncion;
	}
	public String getPrimer_nombre_madre() {
		return primer_nombre_madre;
	}
	public String getSegundo_nombre_madre() {
		return segundo_nombre_madre;
	}
	public String getPrimer_apellido_madre() {
		return primer_apellido_madre;
	}
	public String getTercer_nombre_madre() {
		return tercer_nombre_madre;
	}
	public String getSegundo_apellido_madre() {
		return segundo_apellido_madre;
	}
	public String getApellido_casada_madre() {
		return apellido_casada_madre;
	}
	public String getPrimer_nombre_padre() {
		return primer_nombre_padre;
	}
	public String getSegundo_nombre_padre() {
		return segundo_nombre_padre;
	}
	public String getPrimer_apellido_padre() {
		return primer_apellido_padre;
	}
	public String getTercer_nombre_padre() {
		return tercer_nombre_padre;
	}
	public String getSegundo_apellido_padre() {
		return segundo_apellido_padre;
	}
	public String getFolio_nac() {
		return folio_nac;
	}
	public String getPartida_nac() {
		return partida_nac;
	}
	public String getLibro_nac() {
		return libro_nac;
	}
	public void setIddetallefalle(Long iddetallefalle) {
		this.iddetallefalle = iddetallefalle;
	}
	public void setIdencabezadofalle(Long idencabezadofalle) {
		this.idencabezadofalle = idencabezadofalle;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public void setTipo_entrega(Long tipo_entrega) {
		this.tipo_entrega = tipo_entrega;
	}
	public void setFecha_defuncion(Date fecha_defuncion) {
		this.fecha_defuncion = fecha_defuncion;
	}
	public void setFecha_inscripcion_defuncion(Date fecha_inscripcion_defuncion) {
		this.fecha_inscripcion_defuncion = fecha_inscripcion_defuncion;
	}
	public void setOrden_cedula(String orden_cedula) {
		this.orden_cedula = orden_cedula;
	}
	public void setRegistro_cedula(Long registro_cedula) {
		this.registro_cedula = registro_cedula;
	}
	public void setDepto_extension(Long depto_extension) {
		this.depto_extension = depto_extension;
	}
	public void setMpio_extension(Long mpio_extension) {
		this.mpio_extension = mpio_extension;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public void setPais_nacimiento(Long pais_nacimiento) {
		this.pais_nacimiento = pais_nacimiento;
	}
	public void setDepto_nacimiento(Long depto_nacimiento) {
		this.depto_nacimiento = depto_nacimiento;
	}
	public void setMpio_nacimiento(Long mpio_nacimiento) {
		this.mpio_nacimiento = mpio_nacimiento;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public void setPrimer_nombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}
	public void setSegundo_nombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}
	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}
	public void setTercer_nombre(String tercer_nombre) {
		this.tercer_nombre = tercer_nombre;
	}
	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}
	public void setApellido_casada(String apellido_casada) {
		this.apellido_casada = apellido_casada;
	}
	public void setCui(Long cui) {
		this.cui = cui;
	}
	public void setDepto_inscripcion(Long depto_inscripcion) {
		this.depto_inscripcion = depto_inscripcion;
	}
	public void setMpio_inscripcion(Long mpio_inscripcion) {
		this.mpio_inscripcion = mpio_inscripcion;
	}
	public void setInscripcion_defuncion(Long inscripcion_defuncion) {
		this.inscripcion_defuncion = inscripcion_defuncion;
	}
	public void setPais_defuncion(Long pais_defuncion) {
		this.pais_defuncion = pais_defuncion;
	}
	public void setDepto_defuncion(Long depto_defuncion) {
		this.depto_defuncion = depto_defuncion;
	}
	public void setMpio_defuncion(Long mpio_defuncion) {
		this.mpio_defuncion = mpio_defuncion;
	}
	public void setCausa_defuncion(String causa_defuncion) {
		this.causa_defuncion = causa_defuncion;
	}
	public void setPrimer_nombre_madre(String primer_nombre_madre) {
		this.primer_nombre_madre = primer_nombre_madre;
	}
	public void setSegundo_nombre_madre(String segundo_nombre_madre) {
		this.segundo_nombre_madre = segundo_nombre_madre;
	}
	public void setPrimer_apellido_madre(String primer_apellido_madre) {
		this.primer_apellido_madre = primer_apellido_madre;
	}
	public void setTercer_nombre_madre(String tercer_nombre_madre) {
		this.tercer_nombre_madre = tercer_nombre_madre;
	}
	public void setSegundo_apellido_madre(String segundo_apellido_madre) {
		this.segundo_apellido_madre = segundo_apellido_madre;
	}
	public void setApellido_casada_madre(String apellido_casada_madre) {
		this.apellido_casada_madre = apellido_casada_madre;
	}
	public void setPrimer_nombre_padre(String primer_nombre_padre) {
		this.primer_nombre_padre = primer_nombre_padre;
	}
	public void setSegundo_nombre_padre(String segundo_nombre_padre) {
		this.segundo_nombre_padre = segundo_nombre_padre;
	}
	public void setPrimer_apellido_padre(String primer_apellido_padre) {
		this.primer_apellido_padre = primer_apellido_padre;
	}
	public void setTercer_nombre_padre(String tercer_nombre_padre) {
		this.tercer_nombre_padre = tercer_nombre_padre;
	}
	public void setSegundo_apellido_padre(String segundo_apellido_padre) {
		this.segundo_apellido_padre = segundo_apellido_padre;
	}
	public void setFolio_nac(String folio_nac) {
		this.folio_nac = folio_nac;
	}
	public void setPartida_nac(String partida_nac) {
		this.partida_nac = partida_nac;
	}
	public void setLibro_nac(String libro_nac) {
		this.libro_nac = libro_nac;
	}

	
	

}
