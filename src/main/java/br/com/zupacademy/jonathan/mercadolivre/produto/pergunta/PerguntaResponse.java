package br.com.zupacademy.jonathan.mercadolivre.produto.pergunta;

public class PerguntaResponse {

	private Long id;
	private String titulo;
	
	public PerguntaResponse(Pergunta pergunta) {
		this.id = pergunta.getId();
		this.titulo = pergunta.getTitulo();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}
}
