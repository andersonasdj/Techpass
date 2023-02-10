package br.com.techgold.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techgold.backup.BackupDb;
import br.com.techgold.dto.BackupDTO;
import br.com.techgold.model.Banco;
import br.com.techgold.repository.BancoRepository;

@RestController
@RequestMapping("backup")
public class BackupController {
	
	@Autowired
	BancoRepository repository;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("executar")
	public String gerar() throws InterruptedException, IOException {
		
		if(repository.count() != 0) {
			BackupDb bkp = new BackupDb();
			return bkp.execute(repository.findAll().get(0));
		}else {
			return "Primeiro você deve configurar o backup";
		}
		
	}
	
	@GetMapping("listar")
	public BackupDTO listar() {
		
		if(repository.count() != 0) {
			List<Banco> config = repository.findAll();
			
			BackupDTO backupDTO = new BackupDTO(
					config.get(0).getId(),
					config.get(0).getNome(),
					config.get(0).getUsuario(),
					config.get(0).getSenha(),
					config.get(0).getIp(),
					config.get(0).getCaminho(),
					config.get(0).getLocalsave());
			
			return backupDTO;
		}else
			return null;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("configurar")
	public String configurar(@RequestBody BackupDTO dados) {
		
		if(repository.count() == 0) {
			repository.save(new Banco(dados));
		}else {
			return "Já existe configuração";
		}
		return "Configurado com sucesso";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public String atualizar(@RequestBody BackupDTO dados) {
		
		if(repository.existsById(dados.id())) {
			repository.saveAndFlush(new Banco(dados));
			return "Configurações atualizadas com sucesso!";
		}else {
			return "Erro ao atualiza as configurações!";
		}
	}
	
}
