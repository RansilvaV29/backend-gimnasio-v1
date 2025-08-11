package com.espe.gimnasio.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the musculos database table.
 * 
 */
@Entity
@Table(name="musculos")
@NamedQuery(name="Musculo.findAll", query="SELECT m FROM Musculo m")
public class Musculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	//bi-directional many-to-one association to EjercicioMusculo
	@OneToMany(mappedBy="musculo")
	private List<EjercicioMusculo> ejercicioMusculos;

	public Musculo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		ejercicioMusculo.setMusculo(this);

		return ejercicioMusculo;
	}

	public EjercicioMusculo removeEjercicioMusculo(EjercicioMusculo ejercicioMusculo) {
		getEjercicioMusculos().remove(ejercicioMusculo);
		ejercicioMusculo.setMusculo(null);

		return ejercicioMusculo;
	}

}