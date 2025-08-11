package com.espe.gimnasio.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the historialcorporal database table.
 * 
 */
@Entity
@NamedQuery(name="Historialcorporal.findAll", query="SELECT h FROM Historialcorporal h")
public class Historialcorporal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_historial")
	private Integer idHistorial;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_control")
	private Date fechaControl;

	@Column(name="peso_kg")
	private BigDecimal pesoKg;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Historialcorporal() {
	}

	public Integer getIdHistorial() {
		return this.idHistorial;
	}

	public void setIdHistorial(Integer idHistorial) {
		this.idHistorial = idHistorial;
	}

	public Date getFechaControl() {
		return this.fechaControl;
	}

	public void setFechaControl(Date fechaControl) {
		this.fechaControl = fechaControl;
	}

	public BigDecimal getPesoKg() {
		return this.pesoKg;
	}

	public void setPesoKg(BigDecimal pesoKg) {
		this.pesoKg = pesoKg;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}