package com.espe.gimnasio.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the ejercicio_musculo database table.
 * 
 */
@Entity
@Table(name="ejercicio_musculo")
@NamedQuery(name="EjercicioMusculo.findAll", query="SELECT e FROM EjercicioMusculo e")
public class EjercicioMusculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Ejercicio
	@ManyToOne
	private Ejercicio ejercicio;

	//bi-directional many-to-one association to Musculo
	@ManyToOne
	private Musculo musculo;

	public EjercicioMusculo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ejercicio getEjercicio() {
		return this.ejercicio;
	}

	public void setEjercicio(Ejercicio ejercicio) {
		this.ejercicio = ejercicio;
	}

	public Musculo getMusculo() {
		return this.musculo;
	}

	public void setMusculo(Musculo musculo) {
		this.musculo = musculo;
	}

}