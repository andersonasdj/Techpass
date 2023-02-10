package br.com.techgold.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techgold.model.Roles;
import br.com.techgold.repository.RolesRepository;

@RestController
@RequestMapping("roles")
public class RolesController {

	@Autowired
	RolesRepository repository;
	
	@GetMapping
	public List<Roles> listar() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Roles buscarPorId(@PathVariable("id") Long id) {
		return repository.findById(id).get();
	}
	
	@DeleteMapping("/{id}")
	public String deletePorId(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return "Role excluída com sucesso!";
	}
	
	@PutMapping
	public Roles atualizar(@RequestBody Roles role) {
		if(repository.existsById(role.getId())) {
			return repository.saveAndFlush(role);
		}else {
			return null;
		}
	}
	
	@PostMapping
	public String salvar(@RequestBody Roles roles) {
		if(repository.existsByName(roles.getName().toUpperCase())) {
			return "Role já existe!";
		}else {
			repository.save(roles);
			return "Role criada com sucesso!";
		}
		
	}
}
