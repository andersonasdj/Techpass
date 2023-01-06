package br.com.techgold.model;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notas")
public class Nota {

	@Id
	//@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String conteudo;
	private String senha;
	private String observacao;
	@DateTimeFormat
	private LocalDateTime atualizacao;

	@OneToOne
	private Cliente cliente;

	public Nota() {
	}

	public Nota(Long id, String nome, String conteudo, String senha, String observacao, Cliente cliente, LocalDateTime atualizacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.conteudo = conteudo;
		this.senha = senha;
		this.observacao = observacao;
		this.cliente = cliente;
		this.atualizacao = atualizacao;
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

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	 public void setAtualizacao(LocalDateTime atualizacao) {
		this.atualizacao = atualizacao;
	}
	 
	public LocalDateTime getAtualizacao() {
		return atualizacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, conteudo, id, nome, observacao, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(conteudo, other.conteudo)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(observacao, other.observacao) && Objects.equals(senha, other.senha);
	}

}
