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
@Table(name="TWS_BITACORAFALLE")
@SequenceGenerator(name = "default_gen", sequenceName = "SEQ_IDBITACORAFALLE", allocationSize = 1)
public class twsBitacora implements Serializable{
	
	@GeneratedValue(generator="default_gen")
	@Id
	@Column(name="idbitacorafalle")
	private Long  idbitacorafalle;
	@Column(name="correlativoenvio")
	private Long  correlativoenvio;
	@Column(name="fechacreacion")
	private Date fechacreacion;
	@Column(name="tipoentrega")
	private Long tipoentrega;
	@Column(name="totalregistrosrecibidos")
	private Long totalregistrosrecibidos;
	@Column(name="fechainicioregistrodata")
	private Date fechainicioregistrodata;
	@Column(name="fechafinregistrodata")
	private Date fechafinregistrodata;
	@Column(name="totalregistrosinsertados")
	private Long totalregistrosinsertados;
	@Column(name="totalregistrosnoinsertados")
	private Long totalregistrosnoinsertados;
	@Column(name="fechainiciobackup")
	private Date fechainiciobackup;
	@Column(name="fechafinbackup")
	private Date fechafinbackup;
	@Column(name="totalregistrosbackup")
	private Long totalregistrosbackup;
	@Column(name="estado")
	private Long estado;
	
	
	public twsBitacora() {
	}


	public twsBitacora(Long idbitacorafalle, Long correlativoenvio, Date fechacreacion, Long tipoentrega,
			Long totalregistrosrecibidos, Date fechainicioregistrodata, Date fechafinregistrodata,
			Long totalregistrosinsertados, Long totalregistrosnoinsertados, Date fechainiciobackup, Date fechafinbackup,
			Long totalregistrosbackup, Long estado) {
		this.idbitacorafalle = idbitacorafalle;
		this.correlativoenvio = correlativoenvio;
		this.fechacreacion = fechacreacion;
		this.tipoentrega = tipoentrega;
		this.totalregistrosrecibidos = totalregistrosrecibidos;
		this.fechainicioregistrodata = fechainicioregistrodata;
		this.fechafinregistrodata = fechafinregistrodata;
		this.totalregistrosinsertados = totalregistrosinsertados;
		this.totalregistrosnoinsertados = totalregistrosnoinsertados;
		this.fechainiciobackup = fechainiciobackup;
		this.fechafinbackup = fechafinbackup;
		this.totalregistrosbackup = totalregistrosbackup;
		this.estado = estado;
	}


	public Long getIdbitacorafalle() {
		return idbitacorafalle;
	}


	public Long getCorrelativoenvio() {
		return correlativoenvio;
	}


	public Date getFechacreacion() {
		return fechacreacion;
	}


	public Long getTipoentrega() {
		return tipoentrega;
	}


	public Long getTotalregistrosrecibidos() {
		return totalregistrosrecibidos;
	}


	public Date getFechainicioregistrodata() {
		return fechainicioregistrodata;
	}


	public Date getFechafinregistrodata() {
		return fechafinregistrodata;
	}


	public Long getTotalregistrosinsertados() {
		return totalregistrosinsertados;
	}


	public Long getTotalregistrosnoinsertados() {
		return totalregistrosnoinsertados;
	}


	public Date getFechainiciobackup() {
		return fechainiciobackup;
	}


	public Date getFechafinbackup() {
		return fechafinbackup;
	}


	public Long getTotalregistrosbackup() {
		return totalregistrosbackup;
	}


	public Long getEstado() {
		return estado;
	}


	public void setIdbitacorafalle(Long idbitacorafalle) {
		this.idbitacorafalle = idbitacorafalle;
	}


	public void setCorrelativoenvio(Long correlativoenvio) {
		this.correlativoenvio = correlativoenvio;
	}


	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}


	public void setTipoentrega(Long tipoentrega) {
		this.tipoentrega = tipoentrega;
	}


	public void setTotalregistrosrecibidos(Long totalregistrosrecibidos) {
		this.totalregistrosrecibidos = totalregistrosrecibidos;
	}


	public void setFechainicioregistrodata(Date fechainicioregistrodata) {
		this.fechainicioregistrodata = fechainicioregistrodata;
	}


	public void setFechafinregistrodata(Date fechafinregistrodata) {
		this.fechafinregistrodata = fechafinregistrodata;
	}


	public void setTotalregistrosinsertados(Long totalregistrosinsertados) {
		this.totalregistrosinsertados = totalregistrosinsertados;
	}


	public void setTotalregistrosnoinsertados(Long totalregistrosnoinsertados) {
		this.totalregistrosnoinsertados = totalregistrosnoinsertados;
	}


	public void setFechainiciobackup(Date fechainiciobackup) {
		this.fechainiciobackup = fechainiciobackup;
	}


	public void setFechafinbackup(Date fechafinbackup) {
		this.fechafinbackup = fechafinbackup;
	}


	public void setTotalregistrosbackup(Long totalregistrosbackup) {
		this.totalregistrosbackup = totalregistrosbackup;
	}


	public void setEstado(Long estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("twsBitacora [idbitacorafalle=").append(idbitacorafalle).append(", correlativoenvio=")
				.append(correlativoenvio).append(", fechacreacion=").append(fechacreacion).append(", tipoentrega=")
				.append(tipoentrega).append(", totalregistrosrecibidos=").append(totalregistrosrecibidos)
				.append(", fechainicioregistrodata=").append(fechainicioregistrodata).append(", fechafinregistrodata=")
				.append(fechafinregistrodata).append(", totalregistrosinsertados=").append(totalregistrosinsertados)
				.append(", totalregistrosnoinsertados=").append(totalregistrosnoinsertados)
				.append(", fechainiciobackup=").append(fechainiciobackup).append(", fechafinbackup=")
				.append(fechafinbackup).append(", totalregistrosbackup=").append(totalregistrosbackup)
				.append(", estado=").append(estado).append("]");
		return builder.toString();
	}
}
