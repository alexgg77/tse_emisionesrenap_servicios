package com.example.springsocial.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.example.springsocial.genericModel.BaseModelWithCompanyAndIsActive;

@Entity
@Table(name = "solicitud_firma_electronica")
@SequenceGenerator(name = "default_gen", sequenceName = "solicitud_firma_sequence", allocationSize = 1)
public class SolicitudFirmaElectronica extends BaseModelWithCompanyAndIsActive {
	private String nombre, cui, correo, rutaArchivoSolicitud, rutaArchivoFirma, observacion;
	private Boolean esVerificado, esRechazado, esFinalizado;
	private Date fechaVerificado, fechaFinalizado, fechaExpiracion;
	
	public String getNombre() {		return nombre;	}
	public void setNombre(String nombre) {		this.nombre = nombre;	}
	
	public String getCui() {		return cui;	}
	public void setCui(String cui) {		this.cui = cui;	}
	
	public String getCorreo() {		return correo;	}
	public void setCorreo(String correo) {		this.correo = correo;	}
	
	public String getRutaArchivoSolicitud() {		return rutaArchivoSolicitud;	}
	public void setRutaArchivoSolicitud(String rutaArchivoSolicitud) {		this.rutaArchivoSolicitud = rutaArchivoSolicitud;	}
	
	public String getRutaArchivoFirma() {		return rutaArchivoFirma;	}
	public void setRutaArchivoFirma(String rutaArchivoFirma) {		this.rutaArchivoFirma = rutaArchivoFirma;	}

	public String getObservacion() {		return observacion;	}
	public void setObservacion(String observacion) {		this.observacion = observacion;	}
	
	public Boolean getEsVerificado() {		return esVerificado;	}
	public void setEsVerificado(Boolean esVerificado) {		this.esVerificado = esVerificado;	}
	
	public Boolean getEsRechazado() {		return esRechazado;	}
	public void setEsRechazado(Boolean esRechazado) {		this.esRechazado = esRechazado;	}
	
	public Boolean getEsFinalizado() {		return esFinalizado;	}
	public void setEsFinalizado(Boolean esFinalizado) {		this.esFinalizado = esFinalizado;	}
	
	public Date getFechaVerificado() {		return fechaVerificado;	}
	public void setFechaVerificado(Date fechaVerificado) {		this.fechaVerificado = fechaVerificado;	}
	
	public Date getFechaFinalizado() {		return fechaFinalizado;	}
	public void setFechaFinalizado(Date fechaFinalizado) {		this.fechaFinalizado = fechaFinalizado;	}

	public Date getFechaExpiracion() {		return fechaExpiracion;	}
	public void setFechaExpiracion(Date fechaExpiracion) {		this.fechaExpiracion = fechaExpiracion;	}
}