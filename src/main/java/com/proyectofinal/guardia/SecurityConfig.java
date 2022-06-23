package com.proyectofinal.guardia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.proyectofinal.guardia.service.impl.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userDetailsService;
	
	@Lazy
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
    }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/css/**", "/audios/**", "/images/**", "/js/**", "/webfonts/**").permitAll()
		.antMatchers("/views/usuario/administrar").hasAnyRole("ADMIN")
		.antMatchers("/views/usuario/crear").hasAnyRole("ADMIN")
		.antMatchers("/views/usuario/editar").hasAnyRole("ADMIN")
		.antMatchers("/views/empleado/administrar").hasAnyRole("ADMIN")
		.antMatchers("/views/empleado/crear").hasAnyRole("ADMIN")
		.antMatchers("/views/empleado/editar").hasAnyRole("ADMIN")
		.antMatchers("/views/proveedor/administrar").hasAnyRole("ADMIN")
		.antMatchers("/views/proveedor/crear").hasAnyRole("ADMIN")
		.antMatchers("/views/proveedor/editar").hasAnyRole("ADMIN")
		.antMatchers("/views/vehiculo/administrar").hasAnyRole("ADMIN")
		.antMatchers("/views/vehiculo/crear").hasAnyRole("ADMIN")
		.antMatchers("/views/vehiculo/editar").hasAnyRole("ADMIN")
		.antMatchers("/views/ingresos-empleado").hasAnyRole("GUARDIA","ADMIN")
		.antMatchers("/views/ingresos-empleado/**").hasAnyRole("GUARDIA","ADMIN")
		.antMatchers("/views/ingresos-proveedor").hasAnyRole("GUARDIA","ADMIN")
		.antMatchers("/views/ingresos-proveedor/**").hasAnyRole("GUARDIA","ADMIN")
		.antMatchers("/views/ronda").hasAnyRole("GUARDIA","ADMIN")
		.antMatchers("/views/ronda/**").hasAnyRole("GUARDIA","ADMIN")
		
		.antMatchers("/views/retiro-material/autorizaciones").hasAnyRole("GUARDIA","ADMIN")
		.antMatchers("/views/retiro-material/retiro/**").hasAnyRole("GUARDIA","ADMIN")
		.antMatchers("/views/retiro-material/nueva-autorizacion").hasAnyRole("AUTORIZADOR","ADMIN")
		.antMatchers("/views/retiro-material/nueva-autorizacion/**").hasAnyRole("AUTORIZADOR","ADMIN")
		.antMatchers("/views/retiro-material/autorizaciones/**").hasAnyRole("AUTORIZADOR","GUARDIA","ADMIN")
		
		.antMatchers("/views/sector-trabajo/administrar").hasAnyRole("ADMIN")
		.antMatchers("/views/material/administrar").hasAnyRole("ADMIN")
		
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().permitAll();
	}
}
