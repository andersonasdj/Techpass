package br.com.techgold.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techgold.security.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {
	
	boolean existsByName(String name);

}
