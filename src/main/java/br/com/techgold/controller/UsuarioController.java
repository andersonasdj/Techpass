package br.com.techgold.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techgold.dto.UsuarioDto;
import br.com.techgold.model.Usuario;
import br.com.techgold.model.UsuarioDtoSenha;
import br.com.techgold.repository.UsuarioRepository;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository repository;
	
	@PostMapping("create")
	public String saveUserInitial(@RequestBody UsuarioDto dados) {
		if(repository.findAll().isEmpty()) {
			repository.save(new Usuario(dados));
			System.out.println("aqui!!");
			return "Usuário criado com sucesso!";
		}else {
			return "Usuário principal já foi criado! ";
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listaUsuarios(){
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	@GetMapping("perfil")
	public String getNomessesao()
	{
	   return (((SecurityContext) SecurityContextHolder.getContext()).getAuthentication().getName());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(repository.findById(id).get());
	}
	
	@PutMapping
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario user) {
		if(repository.existsById(user.getId())) {
			String password = repository.findById(user.getId()).get().getPassword();
			user.setPassword(password);
		}
		return ResponseEntity.ok().body(repository.saveAndFlush(user));
	}
	
	@PutMapping("senha")
	public String atualizarSenha(@RequestBody UsuarioDtoSenha dado) {
		Usuario usuarioUpdate = repository.getById(dado.id());
		usuarioUpdate.setPassword(usuarioUpdate.atualizaPassword(dado.password()));
		repository.saveAndFlush(usuarioUpdate);
		return "Senha Atualizada com sucesso!";
	}
	
	@PostMapping
	public Usuario salvarUsuario(@RequestBody UsuarioDto dados) {
		return repository.save(new Usuario(dados));
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return "Delatado com sucesso";
		}else {
			return "usuário não encontrado!";
		}
	}
}
