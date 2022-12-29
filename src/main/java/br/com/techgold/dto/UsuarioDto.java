package br.com.techgold.dto;

import br.com.techgold.model.Endereco;

public record UsuarioDto(
		Long id,
		String nome,
		String usuario,
		String senha,
		Endereco endereco,
		String email) {


}
