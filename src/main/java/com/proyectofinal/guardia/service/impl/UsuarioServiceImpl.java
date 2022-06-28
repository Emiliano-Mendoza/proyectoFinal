package com.proyectofinal.guardia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.RolJPARepository;
import com.proyectofinal.guardia.dao.UsuarioJPARepository;
import com.proyectofinal.guardia.domain.Rol;
import com.proyectofinal.guardia.domain.Usuario;
import com.proyectofinal.guardia.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioJPARepository usuarioRepo;

	@Autowired
	private RolJPARepository rolesRepo;

	@Override
	public Usuario crearUsuario(Usuario us) {

		String passCrip = new BCryptPasswordEncoder().encode(us.getContraseña());

		us.setContraseña(passCrip);

		return usuarioRepo.save(us);
	}
	
	@Override
	public Usuario editarUsuario(Usuario us) {
		
		if(us.getContraseña() != null && us.getContraseña().length()>= 6) {
			
			String passCrip = new BCryptPasswordEncoder().encode(us.getContraseña());
			us.setContraseña(passCrip);
			
		}else {			
			us.setContraseña(usuarioRepo.findById(us.getIdUsuario()).orElseThrow().getContraseña());
		}
		

		return usuarioRepo.save(us);
	}


	@Override
	public List<Usuario> getAllUsuario() {
		return usuarioRepo.findAll();
	}

	@Override
	public Optional<Usuario> findById(int idUsuario) {
		return usuarioRepo.findById(idUsuario);
	}

	@Override
	public Usuario findByUsuario(String us) {
		return usuarioRepo.findByUsername(us);
	}

	@Override
	public Usuario crearUsuario(String username, String contraseña, String nombre, String apellido, Boolean activo,
			List<Integer> roles) {

		Usuario usuarioNuevo = new Usuario();
		usuarioNuevo.setUsername(username);
		usuarioNuevo.setNombre(nombre);
		usuarioNuevo.setApellido(apellido);
		usuarioNuevo.setActivo(activo);

		String passCrip = new BCryptPasswordEncoder().encode(contraseña);

		usuarioNuevo.setContraseña(passCrip);
		
		usuarioNuevo.getRoles().clear();
		
		roles.forEach((rol) -> {
			usuarioNuevo.getRoles().add(rolesRepo.findById(rol).get());
		});

		return usuarioRepo.save(usuarioNuevo);
	}

	@Override
	public Usuario editarUsuario(int idUsuario, String contraseña, String nombre, String apellido, Boolean activo,
			List<Integer> roles) {

		Usuario us = usuarioRepo.findById(idUsuario).orElseThrow();

		us.setActivo(activo);
		us.setApellido(apellido);
		us.setNombre(nombre);
		
		if(contraseña != null && contraseña.length()>= 6){
			String passCrip = new BCryptPasswordEncoder().encode(contraseña);
			us.setContraseña(passCrip);
		}
		
		us.getRoles().clear();
		
		roles.forEach((rol) -> {
			us.getRoles().add(rolesRepo.findById(rol).get());
		});
				
		return usuarioRepo.save(us);
	}

	@Override
	public List<Rol> obtenerRoles() {

		return rolesRepo.findAll();
	}

	@Override
	public List<Usuario> obtenerUsuarioActivos() {

		return usuarioRepo.findAllDisponibles();
	}

	
}
