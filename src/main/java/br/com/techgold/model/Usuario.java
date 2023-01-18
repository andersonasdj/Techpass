package br.com.techgold.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.techgold.dto.UsuarioDto;

@Entity
//@Table(name = "usuarios")
public class Usuario {

	@Id
	// @NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String username;
	private String password;
	private String email;

	// @Embedded
	// private Endereco endereco;

	public Usuario() {
	}

	public Usuario(Long id, String name, String username, String password, String email) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Usuario(UsuarioDto dados) {
		this.id = dados.id();
		this.name = dados.name();
		this.username = dados.username();
		this.password = passwordEncoder().encode(dados.password());
		this.email = dados.email();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
