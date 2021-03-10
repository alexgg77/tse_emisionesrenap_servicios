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
	@Table(name="TRNP_PADRON", schema="PADRONDPI")
	@SequenceGenerator(name = "default_gen", sequenceName = "IDSERIEINVALIDO", allocationSize = 1)
	public class Padron implements Serializable{
		@Id
		@Column(name="nrosolicitudrnp")
		private	long numSolicitud;
		
		@Column(name="nroboleta")
		private	long nroboleta;
		
		@Column(name="fecinscripcion")
		private	Date fechaInscripcion;
		
		
		@Column(name="nom1")
		private	String primerNombre;
		
		@Column(name="nom2")
		private	String segundoNombre;
		
		@Column(name="ape1")
		private	String primerApellido;
		
		@Column(name="ape2")
		private	String segundoApellido;
		
		@Column(name="ape3")
		private	String tercerApellido;
		
		@Column(name="status")
		private	long statusSolicitud;
		
		@Column(name="fecnacimiento")
		private	Date FechaNacimiento;
		
		@Column(name="depnacimiento")
		private	String depNacimiento;
		
		@Column(name="munnacimiento")
		private	String munNacimiento;
		
		@Column(name="genero")
		private	long genero;
		
		@Column(name="alfabetismo")
		private	long alfabetismo;
		
		@Column(name="novidente")
		private	long noVidente;
		
		@Column(name="depresidencia")
		private	long depResidencia;
		
		@Column(name="munresidencia")
		private	long munResidencia;
		
		@Column(name="comresidencia")
		private	long comuResidencia;
		
		@Column(name="cenvotacion")
		private	long centroVotacion ;
		
		@Column(name="direccion")
		private	String direccion;
		
		@Column(name="nrocasa")
		private	String numeroCasa;
		
		@Column(name="nrozona")
		private	String numeroZona;
		
		@Column(name="depempadrona")
		private	long depEmpadronamiento;
		
		@Column(name="mumempadrona")
		private	long munEmpadronamiento;
		
		@Column(name="puestoemprona")
		private	long puestoEmpadronamiento;

		public long getNumSolicitud() {
			return numSolicitud;
		}

		public void setNumSolicitud(long numSolicitud) {
			this.numSolicitud = numSolicitud;
		}

		public long getNroboleta() {
			return nroboleta;
		}

		public void setNroboleta(long nroboleta) {
			this.nroboleta = nroboleta;
		}

		public Date getFechaInscripcion() {
			return fechaInscripcion;
		}

		public void setFechaInscripcion(Date fechaInscripcion) {
			this.fechaInscripcion = fechaInscripcion;
		}

		public String getPrimerNombre() {
			return primerNombre;
		}

		public void setPrimerNombre(String primerNombre) {
			this.primerNombre = primerNombre;
		}

		public String getSegundoNombre() {
			return segundoNombre;
		}

		public void setSegundoNombre(String segundoNombre) {
			this.segundoNombre = segundoNombre;
		}

		public String getPrimerApellido() {
			return primerApellido;
		}

		public void setPrimerApellido(String primerApellido) {
			this.primerApellido = primerApellido;
		}

		public String getSegundoApellido() {
			return segundoApellido;
		}

		public void setSegundoApellido(String segundoApellido) {
			this.segundoApellido = segundoApellido;
		}

		public String getTercerApellido() {
			return tercerApellido;
		}

		public void setTercerApellido(String tercerApellido) {
			this.tercerApellido = tercerApellido;
		}

		public long getStatusSolicitud() {
			return statusSolicitud;
		}

		public void setStatusSolicitud(long statusSolicitud) {
			this.statusSolicitud = statusSolicitud;
		}

		public Date getFechaNacimiento() {
			return FechaNacimiento;
		}

		public void setFechaNacimiento(Date fechaNacimiento) {
			FechaNacimiento = fechaNacimiento;
		}

		public String getDepNacimiento() {
			return depNacimiento;
		}

		public void setDepNacimiento(String depNacimiento) {
			this.depNacimiento = depNacimiento;
		}

		public String getMunNacimiento() {
			return munNacimiento;
		}

		public void setMunNacimiento(String munNacimiento) {
			this.munNacimiento = munNacimiento;
		}

		public long getGenero() {
			return genero;
		}

		public void setGenero(long genero) {
			this.genero = genero;
		}

		public long getAlfabetismo() {
			return alfabetismo;
		}

		public void setAlfabetismo(long alfabetismo) {
			this.alfabetismo = alfabetismo;
		}

		public long getNoVidente() {
			return noVidente;
		}

		public void setNoVidente(long noVidente) {
			this.noVidente = noVidente;
		}

		public long getDepResidencia() {
			return depResidencia;
		}

		public void setDepResidencia(long depResidencia) {
			this.depResidencia = depResidencia;
		}

		public long getMunResidencia() {
			return munResidencia;
		}

		public void setMunResidencia(long munResidencia) {
			this.munResidencia = munResidencia;
		}

		public long getComuResidencia() {
			return comuResidencia;
		}

		public void setComuResidencia(long comuResidencia) {
			this.comuResidencia = comuResidencia;
		}

		public long getCentroVotacion() {
			return centroVotacion;
		}

		public void setCentroVotacion(long centroVotacion) {
			this.centroVotacion = centroVotacion;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getNumeroCasa() {
			return numeroCasa;
		}

		public void setNumeroCasa(String numeroCasa) {
			this.numeroCasa = numeroCasa;
		}

		public String getNumeroZona() {
			return numeroZona;
		}

		public void setNumeroZona(String numeroZona) {
			this.numeroZona = numeroZona;
		}

		public long getDepEmpadronamiento() {
			return depEmpadronamiento;
		}

		public void setDepEmpadronamiento(long depEmpadronamiento) {
			this.depEmpadronamiento = depEmpadronamiento;
		}

		public long getMunEmpadronamiento() {
			return munEmpadronamiento;
		}

		public void setMunEmpadronamiento(long munEmpadronamiento) {
			this.munEmpadronamiento = munEmpadronamiento;
		}

		public long getPuestoEmpadronamiento() {
			return puestoEmpadronamiento;
		}

		public void setPuestoEmpadronamiento(long puestoEmpadronamiento) {
			this.puestoEmpadronamiento = puestoEmpadronamiento;
		}
		
	
	}
		
		
		
		
		
	