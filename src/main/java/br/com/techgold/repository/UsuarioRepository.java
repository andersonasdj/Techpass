package br.com.techgold.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.techgold.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	//@Query("select u from Usuario u where u.usuario = :usuario")
	Usuario findByUsername(/*@Param("usuario")*/ String username);
	
	
	@Query("SELECT u from Usuario u JOIN FETCH u.roles where u.username = :username")
	Usuario findByUsernameFetchRoles(@Param("username") String username);

}
