package com.espe.gimnasio.dto;

public class ListarRutinasDto {
	private Integer id;
    private String nombre;
    private String descripcion;
    private Long cantidadEjercicios;
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
	public Long getCantidadEjercicios() {
		return cantidadEjercicios;
	}
	public void setCantidadEjercicios(Long cantidadEjercicios) {
		this.cantidadEjercicios = cantidadEjercicios;
	}
    
    
}
