package com.proyectofinal.guardia.service;

import java.util.List;
import java.util.Optional;

import com.proyectofinal.guardia.domain.Rol;
import com.proyectofinal.guardia.domain.Usuario;


public interface UsuarioService {
	
	public Usuario crearUsuario(Usuario us);
	public Usuario crearUsuario(String username, String contraseña, String nombre, String apellido, Boolean activo, List<Integer> roles);
	public Usuario editarUsuario(int idUsuario, String contraseña, String nombre, String apellido, Boolean activo, List<Integer> roles);
	public List<Usuario> getAllUsuario();
	public List<Usuario> obtenerUsuarioActivos();
	public Optional<Usuario> findById(int idUsuario);
	public Usuario findByUsuario(String us);
	public List<Rol> obtenerRoles();
}
