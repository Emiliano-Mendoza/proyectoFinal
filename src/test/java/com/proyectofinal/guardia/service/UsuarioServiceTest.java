package com.proyectofinal.guardia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.proyectofinal.guardia.dao.RolJPARepository;
import com.proyectofinal.guardia.dao.UsuarioJPARepository;
import com.proyectofinal.guardia.domain.Rol;
import com.proyectofinal.guardia.domain.Usuario;

@SpringBootTest
public class UsuarioServiceTest {
	
	@MockBean
	private UsuarioJPARepository usuarioRepo;

	@MockBean
	private RolJPARepository rolesRepo;
	
	@Autowired
	private UsuarioService usuarioSrv;
	
	@Test
	public void crearUsuarioShouldReturnNewUsuario() {
		Usuario usuarioNuevo = crearUsuarioPrueba();
		
		when(usuarioRepo.save(usuarioNuevo)).thenReturn(usuarioNuevo);
		
		var resultado = usuarioSrv.crearUsuario(usuarioNuevo);
		
		assertEquals(1, resultado.getIdUsuario());
	}
	
	@Test
	public void editarUsuarioShouldChangePassword() {
		Usuario usuarioNuevo = crearUsuarioPrueba();
		String oldPassword = usuarioNuevo.getContraseña();
		
		when(usuarioRepo.save(usuarioNuevo)).thenReturn(usuarioNuevo);
		
		var resultado = usuarioSrv.editarUsuario(usuarioNuevo);
		
		assertNotEquals(oldPassword, resultado.getContraseña());
	}

	@Test
	public void editarUsuarioShouldSetPassword() {
		Usuario usuarioNuevo = crearUsuarioPrueba();
		usuarioNuevo.setContraseña(null);
		
		Usuario usuarioExistente = crearUsuarioPrueba();

		when(usuarioRepo.save(usuarioNuevo)).thenReturn(usuarioNuevo);
		when(usuarioRepo.findById(usuarioNuevo.getIdUsuario())).thenReturn(Optional.of(usuarioExistente));
		
		var resultado = usuarioSrv.editarUsuario(usuarioNuevo);
		
		assertNotEquals(null, resultado.getContraseña());
	}
	
	@Test
	public void editarUsuarioShouldChangeProperties() {
		Usuario usuarioNuevo = crearUsuarioPrueba();
		String oldPassword = usuarioNuevo.getContraseña();
		usuarioNuevo.setContraseña(null);
		String nuevaContraseña = "newPassword";
		String nuevoNombre = "usuario editado";
		String nuevoApellido = "apellido Editado";
		Boolean activo = true;
		List<Integer> roles = new ArrayList<Integer>();
		Rol rol = new Rol();
		rol.setIdRol(1);
		rol.setRol("usuario");
		roles.add(rol.getIdRol());
		
		Set<Rol> setRol = new HashSet<>();
		setRol.add(rol);
		
		when(usuarioRepo.findById(usuarioNuevo.getIdUsuario())).thenReturn(Optional.of(usuarioNuevo));
		when(rolesRepo.findById(1)).thenReturn(Optional.of(rol));
		when(usuarioRepo.save(usuarioNuevo)).thenReturn(usuarioNuevo);
		
		var resultado = usuarioSrv.editarUsuario(1, nuevaContraseña, nuevoNombre, nuevoApellido, activo, roles);
		
		assertNotEquals(oldPassword, resultado.getContraseña());
		assertEquals(nuevoNombre, resultado.getNombre());
		assertEquals(nuevoApellido, resultado.getApellido());
		assertEquals(activo, resultado.getActivo());
		assertEquals(setRol, resultado.getRoles());
	}
	
	@Test
	public void editarUsuarioShouldChangePropertiesButPassword() {
		Usuario usuarioNuevo = crearUsuarioPrueba();
		
		String nuevoNombre = "usuario editado";
		String nuevoApellido = "apellido Editado";
		Boolean activo = true;
		List<Integer> roles = new ArrayList<Integer>();
		Rol rol = new Rol();
		rol.setIdRol(1);
		rol.setRol("usuario");
		roles.add(rol.getIdRol());
		
		Set<Rol> setRol = new HashSet<>();
		setRol.add(rol);
		
		when(usuarioRepo.findById(usuarioNuevo.getIdUsuario())).thenReturn(Optional.of(usuarioNuevo));
		when(rolesRepo.findById(1)).thenReturn(Optional.of(rol));
		when(usuarioRepo.save(usuarioNuevo)).thenReturn(usuarioNuevo);
		
		var resultado = usuarioSrv.editarUsuario(1, null, nuevoNombre, nuevoApellido, activo, roles);
		
		assertEquals(usuarioNuevo.getContraseña(), resultado.getContraseña());
		assertEquals(nuevoNombre, resultado.getNombre());
		assertEquals(nuevoApellido, resultado.getApellido());
		assertEquals(activo, resultado.getActivo());
		assertEquals(setRol, resultado.getRoles());
	}
	
	@Test
	public void crearUsuarioShouldReturnNuevoUsuario() {
		Usuario usuarioNuevo = crearUsuarioPrueba();

		List<Integer> roles = new ArrayList<Integer>();
		Rol rol = new Rol();
		rol.setIdRol(1);
		rol.setRol("usuario");
		roles.add(rol.getIdRol());
		
		Set<Rol> setRol = new HashSet<>();
		setRol.add(rol);
		
		usuarioNuevo.setRoles(setRol);
		
		when(rolesRepo.findById(1)).thenReturn(Optional.of(rol));
		when(usuarioRepo.save(any(Usuario.class))).thenReturn(usuarioNuevo);
		
		var resultado = usuarioSrv.crearUsuario(usuarioNuevo.getUsername(), usuarioNuevo.getContraseña(), usuarioNuevo.getNombre(), usuarioNuevo.getApellido(), usuarioNuevo.getActivo(), roles);
		
		assertEquals(usuarioNuevo.getUsername(), resultado.getUsername());
		assertEquals(usuarioNuevo.getNombre(), resultado.getNombre());
		assertEquals(usuarioNuevo.getApellido(), resultado.getApellido());
		assertEquals(usuarioNuevo.getActivo(), resultado.getActivo());
		assertEquals(setRol, resultado.getRoles());
	}
	
	private Usuario crearUsuarioPrueba() {
		Usuario usuarioNuevo = new Usuario();
		usuarioNuevo.setIdUsuario(1);
		usuarioNuevo.setNombre("usuario test");
		usuarioNuevo.setApellido("apellido test");
		usuarioNuevo.setContraseña("password");
		usuarioNuevo.setUsername("admin");
		usuarioNuevo.setActivo(true);
		return usuarioNuevo;
	}
}
