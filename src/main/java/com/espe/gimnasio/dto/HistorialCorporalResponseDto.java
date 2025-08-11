package com.espe.gimnasio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class HistorialCorporalResponseDto {
    private Integer idHistorial;
    private Date fechaControl;
    private BigDecimal pesoKg;
    private String nombreUsuario;
	public Integer getIdHistorial() {
		return idHistorial;
	}
	public void setIdHistorial(Integer idHistorial) {
		this.idHistorial = idHistorial;
	}
	public Date getFechaControl() {
		return fechaControl;
	}
	public void setFechaControl(Date fechaControl) {
		this.fechaControl = fechaControl;
	}
	public BigDecimal getPesoKg() {
		return pesoKg;
	}
	public void setPesoKg(BigDecimal pesoKg) {
		this.pesoKg = pesoKg;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


}
