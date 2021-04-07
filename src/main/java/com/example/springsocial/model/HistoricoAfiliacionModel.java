package com.example.springsocial.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="HISTORICO_AFILIACION", schema="WEBCERTIFICACION")
@SequenceGenerator(name = "default_gen", sequenceName = "IDSERIEINVALIDO", allocationSize = 1)
public class HistoricoAfiliacionModel implements Serializable{
		
	@Id	
	@Column(name="CUI")
	private	String cui;
	
	@Column(name="NROBOLETA")
	private	Long numeroBoleta;
	
	@Column(name="ORGANIZACIONPOLITICA")
	private	String organizacionPolitica;
	
	@Column(name="SIGLAS")
	private	String siglas;
	
	@Column(name="HOJA")
	private	Long numeroHoja;	
	
	@Column(name="LINEA")
	private	Long numeroLinea;	
	
	@Column(name="DOCUMENTORENUNCIA")
	private	String documentoRenuncia;	
	
	@Column(name="FECHAAFILIACION")
	private	Date fechaDeAfiliacion;
	
	@Column(name="FECHAOPERACION")
	private	Date fechaDeOperacion;

	@Column(name="FECHADEBAJA")
	private	Date fechaDeBaja;
	
	@Column(name="FECHARENUNCIA")
	private	Date fechaDeRenuncia;

	public String getCui() {
		return cui;
	}

	public Long getNumeroBoleta() {
		return numeroBoleta;
	}

	public String getOrganizacionPolitica() {
		return organizacionPolitica;
	}

	public String getSiglas() {
		return siglas;
	}

	public Long getNumeroHoja() {
		return numeroHoja;
	}

	public Long getNumeroLinea() {
		return numeroLinea;
	}

	public String getDocumentoRenuncia() {
		return documentoRenuncia;
	}

	public Date getFechaDeAfiliacion() {
		return fechaDeAfiliacion;
	}

	public Date getFechaDeOperacion() {
		return fechaDeOperacion;
	}

	public Date getFechaDeBaja() {
		return fechaDeBaja;
	}

	public Date getFechaDeRenuncia() {
		return fechaDeRenuncia;
	}

	
	
	
	
	
}
