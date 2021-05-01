package br.com.zupacademy.jonathan.mercadolivre.produto;

public class ProdutoResponse {

	private Long id;
	
	private String nome;
	
	public ProdutoResponse(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}