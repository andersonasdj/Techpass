package br.com.techgold.dto;

public record UsuarioDtoSalvar(
		Long id,
		String name,
		String username,
		String password,
		String email,
		String roles) {
}
