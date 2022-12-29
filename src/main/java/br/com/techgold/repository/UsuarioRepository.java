package br.com.techgold.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techgold.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
