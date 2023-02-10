package br.com.techgold.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techgold.GeraSenha;
import br.com.techgold.model.Nota;
import br.com.techgold.repository.NotaRepository;

@RestController
@RequestMapping("nota")
public class NotaController {

	@Autowired
	NotaRepository notaRepository;
	
	@GetMapping
	public ResponseEntity<List<Nota>> listar(){
		Sort sort =  Sort.by("cliente.nome").ascending();
		return ResponseEntity.ok().body(notaRepository.findAll(sort));
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Nota nota) throws Exception {
		GeraSenha g = new GeraSenha();
		LocalDateTime data = LocalDateTime.now();
		if(nota.getId() != null){
			nota.setAtualizacao(data);
			if( nota.getSenha().matches("\\d\\d/\\d\\d/\\d\\d\\d\\d")) {
				throw new Exception("Formato não permitido");
			}
			
			nota.setSenha(g.codificar(nota.getSenha()));
			return ResponseEntity.ok().body(notaRepository.saveAndFlush(nota));
		}else
			if( nota.getSenha().matches("\\d\\d/\\d\\d/\\d\\d\\d\\d")) {
				throw new Exception("Formato não permitido");
			}
			nota.setAtualizacao(data);
			nota.setSenha(g.codificar(nota.getSenha()));
			nota.setConteudo(g.codificar(nota.getConteudo()));
			return ResponseEntity.ok().body(notaRepository.save(nota));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Nota> notaPorId(@PathVariable Long id) {
		GeraSenha g = new GeraSenha();
		Nota nota = notaRepository.findById(id).get();
		nota.setSenha(g.decodificar(nota.getSenha()));
		nota.setConteudo(g.decodificar(nota.getConteudo()));
		return ResponseEntity.ok().body(nota);
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Nota nota) throws Exception {
		
		if( nota.getSenha().matches("\\d\\d/\\d\\d/\\d\\d\\d\\d")) {
			throw new Exception("Formato não permitido");
		}
		GeraSenha g = new GeraSenha();
		nota.setSenha(g.codificar(nota.getSenha()));
		nota.setConteudo(g.codificar(nota.getConteudo()));
		LocalDateTime data = LocalDateTime.now();
		nota.setAtualizacao(data);
		return ResponseEntity.ok().body(notaRepository.saveAndFlush(nota));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		notaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
