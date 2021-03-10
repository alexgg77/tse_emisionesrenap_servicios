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
	@Table(name="TCOMPLEMENTO", schema="PADRONCOMPLEMENTO")
	@SequenceGenerator(name = "default_gen", sequenceName = "IDSERIEINVALIDO", allocationSize = 1)
	public class ComplementoPadron implements Serializable{
		@Id
		@Column(name="nroboleta")
		private	String numeroBoleta;
		
		@Column(name="ocupacion")
		private	long profesionOficio;
		
		@Column(name="telefonos")
		private	long telefono;
		
		
		@Column(name="email")
		private	String email;
		
		
		
		
		@Column(name="nom1padre")
		private	String primerNomPadre;
		
		@Column(name="nom2padre")
		private	String segundoNomPadre;
		
		@Column(name="ape1padre")
		private	String primerApePadre;
		
		@Column(name="ape2padre")
		private	String segundoApePadre;
		
		@Column(name="nom1madre")
		private	String primerNomMadre;
		
		@Column(name="nom2madre")
		private	String segundoNomMadre;
		
		@Column(name="ape1madre")
		private	String primerApeMadre;
		
		@Column(name="ape2madre")
		private	String segundoApeMadre;
		
		
		
		@Column(name="serie")
		private	String numeroSerie;
		}


