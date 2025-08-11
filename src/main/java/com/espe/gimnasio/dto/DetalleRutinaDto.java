package com.espe.gimnasio.dto;

public class DetalleRutinaDto {
	private String nombre;
	private Integer ejercicioId;
	private String descripcion;
    private Integer repeticiones;
    private Integer series;
    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getEjercicioId() {
		return ejercicioId;
	}
	public void setEjercicioId(Integer ejercicioId) {
		this.ejercicioId = ejercicioId;
	}

	public Integer getRepeticiones() {
		return repeticiones;
	}
	public void setRepeticiones(Integer repeticiones) {
		this.repeticiones = repeticiones;
	}
	public Integer getSeries() {
		return series;
	}
	public void setSeries(Integer series) {
		this.series = series;
	}
}
