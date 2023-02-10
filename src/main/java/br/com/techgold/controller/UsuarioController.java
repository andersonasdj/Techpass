package br.com.techgold.controller;

import java.util.ArrayList;
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
import br.com.techgold.dto.UsuarioDtoSalvar;
import br.com.techgold.model.Roles;
import br.com.techgold.model.Usuario;
import br.com.techgold.model.UsuarioDtoSenha;
import br.com.techgold.repository.RolesRepository;
import br.com.techgold.repository.UsuarioRepository;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RolesRepository rolesRepository;
	
	@PostMapping("create")
	public String saveUserInitial(@RequestBody UsuarioDto dados) {
		if(usuarioRepository.findAll().isEmpty()) {
			
			Roles roleAdmin = new Roles(null, "ADMIN");
			Roles roleUser = new Roles(null, "USER");
			if(rolesRepository.findAll().isEmpty()) {
				roleAdmin = rolesRepository.save(roleAdmin);
				roleUser = rolesRepository.save(roleUser);
				Usuario usuarioAdmin =  usuarioRepository.save(new Usuario(dados));
				usuarioAdmin.setRoles(roleAdmin);
				usuarioAdmin =  usuarioRepository.saveAndFlush(usuarioAdmin);
			}
			return "Usuário criado com sucesso!";
		}else {
			return "Usuário principal já foi criado! ";
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listaUsuarios(){
		return ResponseEntity.ok().body(usuarioRepository.findAll());
	}
	
	@GetMapping("perfil")
	public String getNomessesao()
	{
	   return (((SecurityContext) SecurityContextHolder.getContext()).getAuthentication().getName());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDtoSalvar> buscarPorId(@PathVariable Long id) {
		
		if(usuarioRepository.existsById(id)) {
			Usuario usuarioEncontrado = usuarioRepository.findById(id).get();
			Roles role = rolesRepository.getById(usuarioEncontrado.getRoles().get(0).getId());
			UsuarioDtoSalvar user = new UsuarioDtoSalvar(usuarioEncontrado.getId(), usuarioEncontrado.getName(), usuarioEncontrado.getUsername(),
					usuarioEncontrado.getPassword(), usuarioEncontrado.getEmail(),role.getName());
			return ResponseEntity.ok().body(user);
		}
		throw new RuntimeException("Erro ao buscar usuário");
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody UsuarioDtoSalvar user) {
		if(usuarioRepository.existsById(user.id())) {
			Roles roles = rolesRepository.findByName(user.roles());
			Usuario userUpdate = usuarioRepository.findById(user.id()).get();
			userUpdate.setName(user.name());
			userUpdate.setUsername(user.username());
			userUpdate.setEmail(user.email());
			userUpdate.setRoles(roles);
			return ResponseEntity.ok().body(usuarioRepository.saveAndFlush(userUpdate));
		}else {
			throw new RuntimeException("Erro ao atualizar o Usuário");
		}
	}
	
	@PutMapping("senha")
	public String atualizarSenha(@RequestBody UsuarioDtoSenha dado) {
		Usuario usuarioUpdate = usuarioRepository.getById(dado.id());
		usuarioUpdate.setPassword(usuarioUpdate.atualizaPassword(dado.password()));
		usuarioRepository.saveAndFlush(usuarioUpdate);
		return "Senha Atualizada com sucesso!";
	}
	
	@PostMapping
	public Usuario salvarUsuario(@RequestBody UsuarioDtoSalvar dados) {
		Roles role = rolesRepository.findByName(dados.roles());
		List<Roles> rolesList = new ArrayList<>();
		rolesList.add(role);
		Usuario usuarioNovo = new Usuario(dados.id(), dados.name(), dados.username(), dados.password(), dados.email(), rolesList );
		return usuarioRepository.save(usuarioNovo);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		if(usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			return "Delatado com sucesso";
		}else {
			return "usuário não encontrado!";
		}
	}
}
