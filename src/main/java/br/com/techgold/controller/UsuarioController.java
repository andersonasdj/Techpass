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

import br.com.techgold.dto.UsuarioDto;
import br.com.techgold.model.Usuario;
import br.com.techgold.repository.UsuarioRepository;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository repository;
	
	
	@GetMapping
	public List<Usuario> listaUsuarios(){
		return repository.findAll();
	}
	
	@GetMapping("id")
	public Usuario buscarPorId(@RequestParam(name = "id") Long id) {
		return repository.findById(id).get();
		
	}
	
	@PutMapping
	public Usuario atualizar(@RequestBody Usuario user) {
		if(repository.existsById(user.getId())) {
			String password = repository.findById(user.getId()).get().getPassword();
			user.setPassword(password);
		}
		
		return repository.saveAndFlush(user);
	}
	
	@PostMapping
	public Usuario salvarUsuario(@RequestBody UsuarioDto dados) {
		return repository.save(new Usuario(dados));
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
