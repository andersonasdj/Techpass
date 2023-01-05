package br.com.techgold.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SistemaController {
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
	@GetMapping("/home")
	public String homePage() {
		return "home.html";
	}
	@GetMapping("/listarclientes")
	public String listarClientes() {
		return "clientes.html";
	}
	@GetMapping("/listarnotas")
	public String listarNotas() {
		return "notas.html";
	}
	@GetMapping("/clienteform")
	public String clienteForm() {
		return "clienteform.html";
	}
	@GetMapping("/listarusuarios")
	public String listarUsuarios() {
		return "usuarios.html";
	}
	@GetMapping("/usuarioform")
	public String usuarioForm() {
		return "usuarioform.html";
	}
	@GetMapping("/notaform")
	public String notaForm() {
		return "notaform.html";
	}
	
}
