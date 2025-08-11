package com.espe.gimnasio.dto;

public class CupoEventoDto {
    private Integer idEvento;
    private int cuposDisponibles;

    public CupoEventoDto() {
    }

    public CupoEventoDto(Integer idEvento, int cuposDisponibles) {
        this.idEvento = idEvento;
        this.cuposDisponibles = cuposDisponibles;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }
}
