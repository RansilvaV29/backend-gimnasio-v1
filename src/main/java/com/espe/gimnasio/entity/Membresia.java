package com.espe.gimnasio.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the membresia database table.
 * 
 */
@Entity
@NamedQuery(name="Membresia.findAll", query="SELECT m FROM Membresia m")
public class Membresia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_membresia")
	private Integer idMembresia;

	private String descripcion;

	@Column(name="nombre_membresia")
	private String nombreMembresia;

	private BigDecimal precio;

	private String vigencia;

	//bi-directional many-to-one association to Historialmambresia
	@OneToMany(mappedBy="membresia")
	private List<Historialmambresia> historialmambresias;

	public Membresia() {
	}

	public Integer getIdMembresia() {
		return this.idMembresia;
	}

	public void setIdMembresia(Integer idMembresia) {
		this.idMembresia = idMembresia;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreMembresia() {
		return this.nombreMembresia;
	}

	public void setNombreMembresia(String nombreMembresia) {
		this.nombreMembresia = nombreMembresia;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getVigencia() {
		return this.vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	public List<Historialmambresia> getHistorialmambresias() {
		return this.historialmambresias;
	}

	public void setHistorialmambresias(List<Historialmambresia> historialmambresias) {
		this.historialmambresias = historialmambresias;
	}

	public Historialmambresia addHistorialmambresia(Historialmambresia historialmambresia) {
		getHistorialmambresias().add(historialmambresia);
		historialmambresia.setMembresia(this);

		return historialmambresia;
	}

	public Historialmambresia removeHistorialmambresia(Historialmambresia historialmambresia) {
		getHistorialmambresias().remove(historialmambresia);
		historialmambresia.setMembresia(null);

		return historialmambresia;
	}

}