package com.espe.gimnasio.dto;

import java.sql.Date;

public class UpdateUserSolicitudDto {
	private Integer idUsuario;
	private String correo;
    private String nombres;
    private String apellidos;
    private String telefono;
    private Integer edad;
    private String cedula;
    private Integer idRol;
    private Date fechaNacimiento;
    
	public UpdateUserSolicitudDto(String correo, String nombres, String apellidos, String telefono, Integer edad,
			String cedula, Integer idRol, Date fechaNacimiento) {
		this.correo = correo;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.edad = edad;
		this.cedula = cedula;
		this.idRol = idRol;
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public Integer getIdRol() {
		return idRol;
	}
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
    
    
}
