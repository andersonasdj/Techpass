package br.com.techgold.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techgold.GeraSenha;

@RestController
@RequestMapping("password")
public class PasswordController {

	@PostMapping
	public String codifica(@RequestBody String pass) {
		return new GeraSenha().codificar(pass);
	}

	
}
