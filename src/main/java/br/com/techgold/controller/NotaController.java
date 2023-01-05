package br.com.techgold.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.techgold.model.Nota;
import br.com.techgold.repository.NotaRepository;

@RestController
@RequestMapping("nota")
public class NotaController {

	@Autowired
	NotaRepository notaRepository;
	
	@GetMapping
	public List<Nota> listar(){
		Sort sort =  Sort.by("cliente.nome").ascending();
		
		return notaRepository.findAll(sort);
	}
	
	@PostMapping
	public Nota salvar(@RequestBody Nota nota) {
		
		LocalDateTime data = LocalDateTime.now();
		
		if(nota.getId() != null){
			nota.setAtualizacao(data);
			return notaRepository.saveAndFlush(nota);
		}else
			nota.setAtualizacao(data);
			return notaRepository.save(nota);
	}
	
	@GetMapping("id")
	public Nota notaPorId(@RequestParam(name = "id") Long id) {
		return notaRepository.findById(id).get();
	}
	
	
	@PutMapping
	public Nota atualizar(@RequestBody Nota nota) {
		LocalDateTime data = LocalDateTime.now();
		nota.setAtualizacao(data);
		return notaRepository.saveAndFlush(nota);
	}
	
	@DeleteMapping
	public void deletar(@RequestParam(name = "id") Long id) {
		notaRepository.deleteById(id);
	}
}
