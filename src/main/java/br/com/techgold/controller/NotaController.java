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
	public ResponseEntity<Nota> salvar(@RequestBody Nota nota) {
		LocalDateTime data = LocalDateTime.now();
		if(nota.getId() != null){
			nota.setAtualizacao(data);
			return ResponseEntity.ok().body(notaRepository.saveAndFlush(nota));
		}else
			nota.setAtualizacao(data);
			return ResponseEntity.ok().body(notaRepository.save(nota));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Nota> notaPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(notaRepository.findById(id).get());
	}
	
	@PutMapping
	public ResponseEntity<Nota> atualizar(@RequestBody Nota nota) {
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
