package com.proyectofinal.guardia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyectofinal.guardia.dao.UsuarioJPARepository;
import com.proyectofinal.guardia.domain.Usuario;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UsuarioJPARepository usuarioRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario us = usuarioRepo.findByUsername(username);
		
		if(us==null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		if(!us.getActivo()) {
			throw new UsernameNotFoundException("Usuario deshabilitado");
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		
		us.getRoles().forEach(rol -> {roles.add(new SimpleGrantedAuthority("ROLE_" + rol.getRol()));});
		
		UserDetails userDet = new User(us.getUsername(), us.getContraseña(), roles);
		
		return userDet;
	}

}
