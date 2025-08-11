package com.espe.gimnasio.dto;

import java.util.List;

public class EjercicioDto {
	private Integer id;
    private String nombre;
    private String descripcion;
    private List<String> musculos; // nombres
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
	public List<String> getMusculos() {
		return musculos;
	}
	public void setMusculos(List<String> musculos) {
		this.musculos = musculos;
	}
    
}
