package com.espe.gimnasio.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the evento database table.
 * 
 */
@Entity
@NamedQuery(name="Evento.findAll", query="SELECT e FROM Evento e")
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_eventos")
	private Integer idEventos;

	@Column(name="cupo_maximo")
	private Integer cupoMaximo;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="hora_inicio")
	private String horaInicio;

	@Column(name="horra_fin")
	private String horraFin;

	@Column(name="nombre_evento")
	private String nombreEvento;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="eventos")
	private List<Usuario> usuarios;

	public Evento() {
	}

	public Integer getIdEventos() {
		return this.idEventos;
	}

	public void setIdEventos(Integer idEventos) {
		this.idEventos = idEventos;
	}

	public Integer getCupoMaximo() {
		return this.cupoMaximo;
	}

	public void setCupoMaximo(Integer cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHorraFin() {
		return this.horraFin;
	}

	public void setHorraFin(String horraFin) {
		this.horraFin = horraFin;
	}

	public String getNombreEvento() {
		return this.nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}