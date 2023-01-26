package br.com.techgold.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techgold.model.Cliente;
import br.com.techgold.repository.ClienteRepository;

@RestController
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	ClienteRepository repository;
	
	@PostMapping
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente ) {
		return ResponseEntity.ok().body(repository.save(cliente));
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar(){
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	@PutMapping
	public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente) {
		return ResponseEntity.ok().body(repository.saveAndFlush(cliente));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(repository.findById(id).get());
		
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return "Delatado com sucesso";
		}else {
			return "Cliente não encontrado!";
		}
	}
}
