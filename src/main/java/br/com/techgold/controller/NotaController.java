package br.com.techgold.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<Nota> listar(){
		return notaRepository.findAll();
	}
	
	@PostMapping
	public Nota salvar(@RequestBody Nota nota) {
		if(nota.getId() != null){
			return notaRepository.saveAndFlush(nota);
		}else
			return notaRepository.save(nota);
	}
	
	@PutMapping
	public Nota atualizar(@RequestBody Nota nota) {
		return notaRepository.saveAndFlush(nota);
	}
}
