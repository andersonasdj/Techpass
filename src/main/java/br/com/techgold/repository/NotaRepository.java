package br.com.techgold.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techgold.model.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long>{

}
