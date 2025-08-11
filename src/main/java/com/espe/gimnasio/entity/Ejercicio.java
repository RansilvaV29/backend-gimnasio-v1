package com.espe.gimnasio.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the ejercicios database table.
 * 
 */
@Entity
@Table(name="ejercicios")
@NamedQuery(name="Ejercicio.findAll", query="SELECT e FROM Ejercicio e")
public class Ejercicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String descripcion;

	private byte[] gif;

	private String nombre;

	//bi-directional many-to-one association to EjercicioMusculo
	@OneToMany(mappedBy="ejercicio")
	private List<EjercicioMusculo> ejercicioMusculos;

	//bi-directional many-to-one association to RutinaEjercicio
	@OneToMany(mappedBy="ejercicio")
	private List<RutinaEjercicio> rutinaEjercicios;

	public Ejercicio() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getGif() {
		return this.gif;
	}

	public void setGif(byte[] gif) {
		this.gif = gif;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<EjercicioMusculo> getEjercicioMusculos() {
		return this.ejercicioMusculos;
	}

	public void setEjercicioMusculos(List<EjercicioMusculo> ejercicioMusculos) {
		this.ejercicioMusculos = ejercicioMusculos;
	}

	public EjercicioMusculo addEjercicioMusculo(EjercicioMusculo ejercicioMusculo) {
		getEjercicioMusculos().add(ejercicioMusculo);
		ejercicioMusculo.setEjercicio(this);

		return ejercicioMusculo;
	}

	public EjercicioMusculo removeEjercicioMusculo(EjercicioMusculo ejercicioMusculo) {
		getEjercicioMusculos().remove(ejercicioMusculo);
		ejercicioMusculo.setEjercicio(null);

		return ejercicioMusculo;
	}

	public List<RutinaEjercicio> getRutinaEjercicios() {
		return this.rutinaEjercicios;
	}

	public void setRutinaEjercicios(List<RutinaEjercicio> rutinaEjercicios) {
		this.rutinaEjercicios = rutinaEjercicios;
	}

	public RutinaEjercicio addRutinaEjercicio(RutinaEjercicio rutinaEjercicio) {
		getRutinaEjercicios().add(rutinaEjercicio);
		rutinaEjercicio.setEjercicio(this);

		return rutinaEjercicio;
	}

	public RutinaEjercicio removeRutinaEjercicio(RutinaEjercicio rutinaEjercicio) {
		getRutinaEjercicios().remove(rutinaEjercicio);
		rutinaEjercicio.setEjercicio(null);

		return rutinaEjercicio;
	}

}