package br.com.zupacademy.jonathan.mercadolivre.produto.opiniao;

public class OpiniaoResponse {

	private Long id;
	private String titulo;
	private Integer nota;
	
	public OpiniaoResponse(Opiniao opiniao) {
		this.id = opiniao.getId();
		this.titulo = opiniao.getTitulo();
		this.nota = opiniao.getNota();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Integer getNota() {
		return nota;
	}
	
}
