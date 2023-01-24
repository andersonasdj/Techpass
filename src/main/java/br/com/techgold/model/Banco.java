package br.com.techgold.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.techgold.dto.BackupDTO;

@Entity
public class Banco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String usuario;
	private String senha;
	private String ip;
	private String caminho;
	private String localsave;
	
	public Banco() {
	}

	public Banco(String nome, String usuario, String senha, String ip, String caminho, String localsave) {
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.ip = ip;
		this.caminho = caminho;
		this.localsave = localsave;
	}

	public Banco(BackupDTO dados) {
		this.id = dados.id();
		this.nome = dados.nome();
		this.usuario = dados.usuario();
		this.senha = dados.senha();
		this.ip = dados.ip();
		this.caminho = dados.caminho();
		this.localsave = dados.localsave();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getLocalsave() {
		return localsave;
	}

	public void setLocalsave(String localsave) {
		this.localsave = localsave;
	}

}
