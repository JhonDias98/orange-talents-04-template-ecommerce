package br.com.zupacademy.jonathan.mercadolivre.produto.opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.jonathan.mercadolivre.produto.Produto;
import br.com.zupacademy.jonathan.mercadolivre.usuario.Usuario;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Range(min = 1, max = 5)
	private Integer nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String descricao;
	@NotNull
	@ManyToOne
	private Usuario usuario;
	@NotNull
	@ManyToOne
	private Produto produto;

	public Opiniao(@NotNull @Range(min = 1, max = 5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 5000) String descricao, @NotNull Usuario usuario, @NotNull Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	@Deprecated
	public Opiniao() {
	}

	public Long getId() {
		return id;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}

}
