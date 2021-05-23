package br.com.zupacademy.jonathan.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import br.com.zupacademy.jonathan.mercadolivre.produto.caracteristica.CaracteristicaDetalheResponse;
import br.com.zupacademy.jonathan.mercadolivre.produto.imagem.Imagem;
import br.com.zupacademy.jonathan.mercadolivre.produto.opiniao.OpiniaoResponse;
import br.com.zupacademy.jonathan.mercadolivre.produto.pergunta.Pergunta;

public class ProdutoDetalheResponse {

	private String nome;
	private BigDecimal valor;
	private String descricao;
	private Set<CaracteristicaDetalheResponse> caracteristicas;
	private Set<String> imagens;
	private SortedSet<String> perguntas;
    private List<OpiniaoResponse> opinioes;
    private double mediaNotas;
    private Integer quantidadeNotas;

	public ProdutoDetalheResponse(Produto produto) {
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.descricao = produto.getDescricao();
		//Usando as funções genéricas do Produto
		this.caracteristicas = produto.mapeiaCaracteristicas(CaracteristicaDetalheResponse::new);
		this.imagens = produto.mapeiaImagens(Imagem::getLink);
        this.perguntas = produto.mapeiaPerguntas(Pergunta::getTitulo);
        this.opinioes = produto.mapeiaOpinioes(OpiniaoResponse::new);
        this.mediaNotas = opinioes.stream().mapToDouble(OpiniaoResponse::getNota).average().orElse(0.0);
        this.quantidadeNotas = opinioes.size();
        
		//Exemplo usando uma classe DTO
//		this.caracteristicas = produto.getCaracteristicas().stream()
//				.map(caracteristica -> new CaracteristicaDetalheResponse(caracteristica)).collect(Collectors.toSet());
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public Set<CaracteristicaDetalheResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getImagens() {
		return imagens;
	}

	public SortedSet<String> getPerguntas() {
		return perguntas;
	}

	public List<OpiniaoResponse> getOpinioes() {
		return opinioes;
	}

	public double getMediaNotas() {
		return mediaNotas;
	}

	public Integer getQuantidadeNotas() {
		return quantidadeNotas;
	}

}
