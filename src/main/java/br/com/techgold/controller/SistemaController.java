package br.com.techgold.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SistemaController {
	
	@GetMapping("/login")
	public String login() {
		return "templates/login.html";
	}
	@GetMapping("/home")
	public String homePage() {
		return "templates/home.html";
	}
	@GetMapping("/listarclientes")
	public String listarClientes() {
		return "templates/clientes.html";
	}
	@GetMapping("/listarnotas")
	public String listarNotas() {
		return "templates/notas.html";
	}
	@GetMapping("/clienteform")
	public String clienteForm() {
		return "templates/clienteform.html";
	}
	@GetMapping("/listarusuarios")
	public String listarUsuarios() {
		return "templates/usuarios.html";
	}
	@GetMapping("/usuarioform")
	public String usuarioForm() {
		return "templates/usuarioform.html";
	}
	@GetMapping("/notaform")
	public String notaForm() {
		return "templates/notaform.html";
	}
	@GetMapping("/backup")
	public String backup() {
		return "templates/backup.html";
	}
	
}
