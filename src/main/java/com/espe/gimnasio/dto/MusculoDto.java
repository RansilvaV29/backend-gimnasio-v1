package com.espe.gimnasio.dto;

public class MusculoDto {
	private Integer id;
    private String nombre;
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
	public MusculoDto(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
    
}
