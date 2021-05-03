package br.com.zupacademy.jonathan.mercadolivre.produto.opiniao;

public class OpiniaoResponse {

	private Long id;
	private String titulo;
	
	public OpiniaoResponse(Opiniao opiniao) {
		this.id = opiniao.getId();
		this.titulo = opiniao.getTitulo();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}
	
}
