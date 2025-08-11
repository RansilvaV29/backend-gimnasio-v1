package com.espe.gimnasio.dto;

import java.math.BigDecimal;
import java.util.Date;

public class HistorialCorporalDto {
	
    private Date fechaControl;
    private BigDecimal pesoKg;
    private Integer idUsuario;

    // Getters y setters


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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
