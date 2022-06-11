package com.proyectofinal.guardia.helpers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class imgConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		//aqui se configura la ruta de los recursos
		registry.addResourceHandler("/recursos/**").addResourceLocations("file:/C:/Guardia/Empleados/recursos/");
	}

	
	
}
