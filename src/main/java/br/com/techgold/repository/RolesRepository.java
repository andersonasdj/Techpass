package br.com.techgold.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techgold.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {
	
	boolean existsByName(String name);
	
	Roles findByName(String name);
	

}
