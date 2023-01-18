package br.com.techgold.dto;

public record UsuarioDto(
		Long id,
		String name,
		String username,
		String password,
		String email) {


}
