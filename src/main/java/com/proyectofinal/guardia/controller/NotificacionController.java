package com.proyectofinal.guardia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectofinal.guardia.helpers.NotificacionHelper;
import com.proyectofinal.guardia.service.NotificacionService;

@Controller
@RequestMapping("/notificaciones")
public class NotificacionController {
	
	@Autowired
	private NotificacionService notiServ;
	
	@GetMapping
	public ResponseEntity<List<NotificacionHelper>> listarNotificaciones(){
		
		return ResponseEntity.ok(notiServ.obtenerNotificaciones());
	}
	
	@PostMapping("/leidas")
	public ResponseEntity<Boolean> marcarNotisLeidas(@RequestBody List<Integer> listaIds){
		
		try {
			notiServ.marcarNotificacionesLeidas(listaIds);
		}catch(Exception e) {
			System.out.println("No se pudieron marcar las notificaciones.");
			ResponseEntity.ok(false);
		}
				
		return ResponseEntity.ok(true);
	}
}
