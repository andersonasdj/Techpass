package br.com.techgold.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techgold.model.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long>{

	List<Nota> findAll(Sort sort);
}
