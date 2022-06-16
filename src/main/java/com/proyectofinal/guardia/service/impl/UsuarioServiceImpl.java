package com.proyectofinal.guardia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.RolJPARepository;
import com.proyectofinal.guardia.dao.UsuarioJPARepository;
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
	public Usuario crearUsuario(Usuario us, List<String> roles) {
		String passCrip = new BCryptPasswordEncoder().encode(us.getContraseña()); 
		
		us.setContraseña(passCrip);

		roles.forEach((rol)->{us.getRoles().add(rolesRepo.findByRol(rol));});
		
		
		return usuarioRepo.save(us);
	}

	@Override
	public Usuario editarUsuario(Usuario us, List<String> roles) {
		
		roles.forEach((rol)->{us.getRoles().add(rolesRepo.findByRol(rol));});
		
		return usuarioRepo.save(us);
	}

}
