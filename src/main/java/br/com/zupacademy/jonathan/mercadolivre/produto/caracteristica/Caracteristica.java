package br.com.zupacademy.jonathan.mercadolivre.produto.caracteristica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.jonathan.mercadolivre.produto.Produto;

@Entity
public class Caracteristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@ManyToOne
	private Produto produto;
	
	public Caracteristica(@NotBlank String nome, @NotBlank String descricao, @Valid Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}
	
	@Deprecated
	public Caracteristica() {
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto getProduto() {
		return produto;
	}

}
