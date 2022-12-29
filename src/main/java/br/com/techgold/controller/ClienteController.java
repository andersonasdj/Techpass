package br.com.techgold.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.techgold.model.Cliente;
import br.com.techgold.repository.ClienteRepository;

@RestController
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	ClienteRepository repository;
	
	@PostMapping
	public Cliente salvar(@RequestBody Cliente cliente ) {
		return repository.save(cliente);
	}
	
	@GetMapping
	public List<Cliente> listar(){
		return repository.findAll();
	}
	
	@PutMapping
	public Cliente atualizar(@RequestBody Cliente cliente) {
		return repository.saveAndFlush(cliente);
	}
	
	@GetMapping("id")
	public Cliente buscarPorId(@RequestParam(name = "id") Long id) {
		return repository.findById(id).get();
		
	}

	@DeleteMapping
	public String delete(@RequestParam(name = "id") Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return "Delatado com sucesso";
		}else {
			return "usuário não encontrado!";
		}
	}
}
