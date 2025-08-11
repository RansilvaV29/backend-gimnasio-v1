package com.espe.gimnasio.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the rutinas database table.
 * 
 */
@Entity
@Table(name="rutinas")
@NamedQuery(name="Rutina.findAll", query="SELECT r FROM Rutina r")
public class Rutina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to RutinaEjercicio
	@OneToMany(mappedBy="rutina")
	private List<RutinaEjercicio> rutinaEjercicios;

	public Rutina() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<RutinaEjercicio> getRutinaEjercicios() {
		return this.rutinaEjercicios;
	}

	public void setRutinaEjercicios(List<RutinaEjercicio> rutinaEjercicios) {
		this.rutinaEjercicios = rutinaEjercicios;
	}

	public RutinaEjercicio addRutinaEjercicio(RutinaEjercicio rutinaEjercicio) {
		getRutinaEjercicios().add(rutinaEjercicio);
		rutinaEjercicio.setRutina(this);

		return rutinaEjercicio;
	}

	public RutinaEjercicio removeRutinaEjercicio(RutinaEjercicio rutinaEjercicio) {
		getRutinaEjercicios().remove(rutinaEjercicio);
		rutinaEjercicio.setRutina(null);

		return rutinaEjercicio;
	}

}