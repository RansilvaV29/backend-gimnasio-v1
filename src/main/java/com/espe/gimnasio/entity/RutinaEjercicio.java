package com.espe.gimnasio.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the rutina_ejercicio database table.
 * 
 */
@Entity
@Table(name="rutina_ejercicio")
@NamedQuery(name="RutinaEjercicio.findAll", query="SELECT r FROM RutinaEjercicio r")
public class RutinaEjercicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer orden;

	private Integer repeticiones;

	private Integer series;

	//bi-directional many-to-one association to Ejercicio
	@ManyToOne
	private Ejercicio ejercicio;

	//bi-directional many-to-one association to Rutina
	@ManyToOne
	private Rutina rutina;

	public RutinaEjercicio() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Integer getRepeticiones() {
		return this.repeticiones;
	}

	public void setRepeticiones(Integer repeticiones) {
		this.repeticiones = repeticiones;
	}

	public Integer getSeries() {
		return this.series;
	}

	public void setSeries(Integer series) {
		this.series = series;
	}

	public Ejercicio getEjercicio() {
		return this.ejercicio;
	}

	public void setEjercicio(Ejercicio ejercicio) {
		this.ejercicio = ejercicio;
	}

	public Rutina getRutina() {
		return this.rutina;
	}

	public void setRutina(Rutina rutina) {
		this.rutina = rutina;
	}

}