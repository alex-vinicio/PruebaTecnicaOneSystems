package com.unosystem.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@Column(name = "ci")
	private String ciUsuarioEcuador;

	@Column(name = "nombre")
	private String nombreUsuario;

	@Column(name = "rol")
	private Long rolUsuario;

	@Column(name = "apellido")
	private String apellidoUsuario;

	@Column(name = "correo")
	private String correoUsuario;

	@Column(name = "contrasenia")
	private String contrasenia;

	@Column(name = "tarjeta_numero_bancario")
	private String numeroTarjeta;

	@Column(name = "telefono")
	private String telefono;

	// constructor sin parametros
	public Usuario() {

	}

	// creacion de getters y setters para acceder a atributos de usuario
	public String getCiUsuarioEcuador() {
		return ciUsuarioEcuador;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setCiUsuarioEcuador(String ciUsuarioEcuador) {
		this.ciUsuarioEcuador = ciUsuarioEcuador;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public String getCorreoUsuario() {
		return correoUsuario;
	}

	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contraseniaUsuario) {
		this.contrasenia = contraseniaUsuario;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public Long getRolUsuario() {
		return rolUsuario;
	}

	public void setRolUsuario(Long rolUsuario) {
		this.rolUsuario = rolUsuario;
	}

}
