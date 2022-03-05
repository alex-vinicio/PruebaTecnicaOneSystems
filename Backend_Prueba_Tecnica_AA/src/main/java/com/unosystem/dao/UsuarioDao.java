package com.unosystem.dao;

import java.util.List;

import com.unosystem.entities.Usuario;

//forma de como ejecutar las consultas sql en forma de transacciones
public interface UsuarioDao {

	List<Usuario> getUsuarios();

	void eliminarUsuario(String ci);

	void registrarUsuario(Usuario usuario);

	Usuario validarUsuarioConCredencial(String mail, String contrasenia);
}
