package br.com.zupacademy.jonathan.mercadolivre.produto.pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.jonathan.mercadolivre.produto.Produto;
import br.com.zupacademy.jonathan.mercadolivre.usuario.Usuario;


public class PerguntaRequest {

	@NotBlank
	private String titulo;
	
	@JsonCreator
	public PerguntaRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public Pergunta toModel(@NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
		return new Pergunta(titulo, usuario, produto);
	}
	
}
