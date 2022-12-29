package br.com.techgold.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techgold.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
