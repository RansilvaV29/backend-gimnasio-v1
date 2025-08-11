package com.espe.gimnasio.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class HistorialMembresiaDto {
	private Integer idUsuario;
	private Integer idMembresia;
	private Date fechaInicio;
	private Date fechaFin;
	private BigDecimal valorMembresiaactual;
	private BigDecimal valorPagado;
	private Boolean estado;
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdMembresia() {
		return idMembresia;
	}
	public void setIdMembresia(Integer idMembresia) {
		this.idMembresia = idMembresia;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public BigDecimal getValorMembresiaactual() {
		return valorMembresiaactual;
	}
	public void setValorMembresiaactual(BigDecimal valorMembresiaactual) {
		this.valorMembresiaactual = valorMembresiaactual;
	}
	public BigDecimal getValorPagado() {
		return valorPagado;
	}
	public void setValorPagado(BigDecimal valorPagado) {
		this.valorPagado = valorPagado;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	

	
}
