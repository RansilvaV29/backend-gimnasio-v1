package com.espe.gimnasio.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the historialmambresia database table.
 * 
 */
@Entity
@NamedQuery(name="Historialmambresia.findAll", query="SELECT h FROM Historialmambresia h")
public class Historialmambresia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_historial_membresias")
	private Integer idHistorialMembresias;

	private Boolean estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	@Column(name="valor_membresiaactual")
	private BigDecimal valorMembresiaactual;

	@Column(name="valor_pagado")
	private BigDecimal valorPagado;

	//bi-directional many-to-one association to Membresia
	@ManyToOne
	@JoinColumn(name="id_membresia")
	private Membresia membresia;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Historialmambresia() {
	}

	public Integer getIdHistorialMembresias() {
		return this.idHistorialMembresias;
	}

	public void setIdHistorialMembresias(Integer idHistorialMembresias) {
		this.idHistorialMembresias = idHistorialMembresias;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public BigDecimal getValorMembresiaactual() {
		return this.valorMembresiaactual;
	}

	public void setValorMembresiaactual(BigDecimal valorMembresiaactual) {
		this.valorMembresiaactual = valorMembresiaactual;
	}

	public BigDecimal getValorPagado() {
		return this.valorPagado;
	}

	public void setValorPagado(BigDecimal valorPagado) {
		this.valorPagado = valorPagado;
	}

	public Membresia getMembresia() {
		return this.membresia;
	}

	public void setMembresia(Membresia membresia) {
		this.membresia = membresia;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}