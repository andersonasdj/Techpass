package br.com.techgold.dto;

public record BackupDTO(
		Long id,
		String nome,
		String usuario,
		String senha,
		String ip,
		String caminho,
		String localsave) {
		

}

