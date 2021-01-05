package com.example.springsocial.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.springsocial.genericModel.BaseModelWithCompanyAndIsActive;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "firma_electronica")
@SequenceGenerator(name = "default_gen", sequenceName = "firma_electronica_sequence", allocationSize = 1)
public class FirmaElectronica extends BaseModelWithCompanyAndIsActive {
	private String clave;
	private String observacion;
	private Date fechaCancelado;

	@ManyToOne
    @JoinColumn(name="solicitud_firma_electronica_id", insertable=false, updatable=false)
    private SolicitudFirmaElectronica solicitudFirmaElectronica; 
    
	@Column(name = "solicitud_firma_electronica_id")
    @JsonProperty("solicitud_firma_electronica_id")
    private Long solicitud_firma_electronica_id;
	public String getClave() {		return clave;	}
	public void setClave(String clave) {		this.clave = clave;	}
	
	public SolicitudFirmaElectronica getSolicitudFirmaElectronica() {		return solicitudFirmaElectronica;	}
	public void setSolicitudFirmaElectronica(SolicitudFirmaElectronica solicitudFirmaElectronica) {		this.solicitudFirmaElectronica = solicitudFirmaElectronica;	}
	public Long getSolicitud_firma_electronica_id() {		return solicitud_firma_electronica_id;	}
	public void setSolicitud_firma_electronica_id(Long solicitud_firma_electronica_id) {		this.solicitud_firma_electronica_id = solicitud_firma_electronica_id;	}
	public String getObservacion() {return observacion;}
	public void setObservacion(String observacion) {	this.observacion = observacion;}
	public Date getFechaCancelado() {return fechaCancelado;}
	public void setFechaCancelado(Date fechaCancelado) {this.fechaCancelado = fechaCancelado;}

}