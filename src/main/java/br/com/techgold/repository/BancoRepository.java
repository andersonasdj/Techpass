package br.com.techgold.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techgold.model.Banco;

public interface BancoRepository extends JpaRepository<Banco, Long>{

}
