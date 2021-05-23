package br.com.zupacademy.jonathan.mercadolivre.produto.caracteristica;

public class CaracteristicaDetalheResponse {

	private String nome;
	private String descricao;

	public CaracteristicaDetalheResponse(Caracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
