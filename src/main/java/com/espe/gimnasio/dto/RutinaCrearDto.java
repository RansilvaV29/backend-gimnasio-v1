package com.espe.gimnasio.dto;

import java.util.List;

public class RutinaCrearDto {
	private Integer id;
	private String nombre;
	private String descripcion;
	private List<DetalleRutinaDto> ejercicios;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public List<DetalleRutinaDto> getEjercicios() {
		return ejercicios;
	}
	public void setEjercicios(List<DetalleRutinaDto> ejercicios) {
		this.ejercicios = ejercicios;
	}
	
	
}
