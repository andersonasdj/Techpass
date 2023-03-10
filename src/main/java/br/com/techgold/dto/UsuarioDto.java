package br.com.techgold.dto;

import java.util.List;

import br.com.techgold.model.Roles;

public record UsuarioDto(
		Long id,
		String name,
		String username,
		String password,
		String email,
		List<Roles> roles) {

}
