package com.espe.gimnasio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class HistorialMembresiaActivaDto {
	private Integer idHistorial;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean estado;
    private BigDecimal valorPagado;
    private BigDecimal valorMembresiaactual;
    private String nombreMembresia;
    private String vigencia;

    // Constructor
    public HistorialMembresiaActivaDto(Integer integer, Date fechaInicio, Date fechaFin,
                                       Boolean estado, BigDecimal bigDecimal, BigDecimal valorMembresiaactual,
                                       String nombreMembresia, String vigencia) {
        this.idHistorial = integer;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.valorPagado = bigDecimal;
        this.valorMembresiaactual = valorMembresiaactual;
        this.nombreMembresia = nombreMembresia;
        this.vigencia = vigencia;
    }

	public Integer getIdHistorial() {
		return idHistorial;
	}

	public void setIdHistorial(Integer idHistorial) {
		this.idHistorial = idHistorial;
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

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public BigDecimal getValorPagado() {
		return valorPagado;
	}

	public void setValorPagado(BigDecimal valorPagado) {
		this.valorPagado = valorPagado;
	}

	public String getNombreMembresia() {
		return nombreMembresia;
	}

	public void setNombreMembresia(String nombreMembresia) {
		this.nombreMembresia = nombreMembresia;
	}

	public String getVigencia() {
		return vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	public BigDecimal getValorMembresiaactual() {
		return valorMembresiaactual;
	}

	public void setValorMembresiaactual(BigDecimal valorMembresiaactual) {
		this.valorMembresiaactual = valorMembresiaactual;
	}
    
    

}
