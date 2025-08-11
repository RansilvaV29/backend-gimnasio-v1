package com.espe.gimnasio.entity;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/*
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
*/

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Integer idUsuario;

	private String apellidos;

	private String cedula;

	private String correo;

	private Integer edad;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	private String nombres;

	private String telefono;
	
	@Column(name = "contrasena")
	private String contrasena;

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	//bi-directional many-to-many association to Evento
	@ManyToMany
	@JoinTable(
		name="asiste"
		, joinColumns={
			@JoinColumn(name="id_usuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_eventos")
			}
		)
	private List<Evento> eventos;

	//bi-directional many-to-one association to Historialcorporal
	@OneToMany(mappedBy="usuario")
	private List<Historialcorporal> historialcorporals;

	//bi-directional many-to-one association to Historialmambresia
	@OneToMany(mappedBy="usuario")
	private List<Historialmambresia> historialmambresias;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="id_rol")
	private Rol rol;

	public Usuario() {
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<Historialcorporal> getHistorialcorporals() {
		return this.historialcorporals;
	}

	public void setHistorialcorporals(List<Historialcorporal> historialcorporals) {
		this.historialcorporals = historialcorporals;
	}

	public Historialcorporal addHistorialcorporal(Historialcorporal historialcorporal) {
		getHistorialcorporals().add(historialcorporal);
		historialcorporal.setUsuario(this);

		return historialcorporal;
	}

	public Historialcorporal removeHistorialcorporal(Historialcorporal historialcorporal) {
		getHistorialcorporals().remove(historialcorporal);
		historialcorporal.setUsuario(null);

		return historialcorporal;
	}

	public List<Historialmambresia> getHistorialmambresias() {
		return this.historialmambresias;
	}

	public void setHistorialmambresias(List<Historialmambresia> historialmambresias) {
		this.historialmambresias = historialmambresias;
	}

	public Historialmambresia addHistorialmambresia(Historialmambresia historialmambresia) {
		getHistorialmambresias().add(historialmambresia);
		historialmambresia.setUsuario(this);

		return historialmambresia;
	}

	public Historialmambresia removeHistorialmambresia(Historialmambresia historialmambresia) {
		getHistorialmambresias().remove(historialmambresia);
		historialmambresia.setUsuario(null);

		return historialmambresia;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	//a partir de aquí son propiedades de spring security 
/*
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    String roleName = rol != null && rol.getNombreRol() != null
	        ? "ROLE_" + rol.getNombreRol()
	        : "ROLE_USER";
	    return Arrays.asList(new SimpleGrantedAuthority(roleName));
	}




	@Override
	public String getPassword() {
		return this.contrasena;
	}

	@Override
	public String getUsername() {
		return this.correo;
	}
	
	@Override 
	public boolean isAccountNonExpired() {
	    return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	    return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	    return true;
	}

	@Override
	public boolean isEnabled() {
	    return true;
	}
	
*/
}