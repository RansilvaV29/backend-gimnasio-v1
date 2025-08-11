package com.espe.gimnasio.dto;

import java.util.List;

public class EjercicioCrearDto {
	private String nombre;
    private String descripcion;
    private byte[] gif;
    private List<Integer> musculoIds;
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
	public byte[] getGif() {
		return gif;
	}
	public void setGif(byte[] gif) {
		this.gif = gif;
	}
	public List<Integer> getMusculoIds() {
		return musculoIds;
	}
	public void setMusculoIds(List<Integer> musculoIds) {
		this.musculoIds = musculoIds;
	}
    
    
}
