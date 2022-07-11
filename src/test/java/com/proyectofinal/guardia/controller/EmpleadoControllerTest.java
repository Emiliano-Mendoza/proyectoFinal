package com.proyectofinal.guardia.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.proyectofinal.guardia.controller.EmpleadoController;
import com.proyectofinal.guardia.domain.Empleado;
import com.proyectofinal.guardia.service.EmpleadoService;
import com.proyectofinal.guardia.service.impl.UserService;

@WebMvcTest(EmpleadoController.class)
public class EmpleadoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmpleadoService mockEmpleadoServ;

	@MockBean
	private UserService userService;

	@Test
	public void crearEmpleadoShouldReturnOk() throws Exception {
		Empleado empleado = new Empleado();
		when(mockEmpleadoServ.validarDatos(empleado)).thenReturn(false);
		
		var resultado = this.mockMvc.perform(get("/crear/?imagen=null&activo=1&sector=1")).andDo(print());
		 
		// .andExpect(content().string(containsString("redirect:/views/empleado/administrar")));
	}
}
