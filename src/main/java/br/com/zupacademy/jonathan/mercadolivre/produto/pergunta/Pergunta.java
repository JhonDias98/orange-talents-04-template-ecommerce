package br.com.zupacademy.jonathan.mercadolivre.produto.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.jonathan.mercadolivre.produto.Produto;
import br.com.zupacademy.jonathan.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String titulo;
	@NotNull
	@CreationTimestamp
	private LocalDateTime instanteCriacao = LocalDateTime.now();
	@ManyToOne
	@NotNull
	private Usuario usuario;
	@ManyToOne
	@NotNull
	private Produto produto;
	
	public Pergunta(@NotBlank String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}

	@Deprecated
	public Pergunta() {}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getInstanteCriacao() {
		return instanteCriacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public Produto getProduto() {
		return produto;
	}

}
