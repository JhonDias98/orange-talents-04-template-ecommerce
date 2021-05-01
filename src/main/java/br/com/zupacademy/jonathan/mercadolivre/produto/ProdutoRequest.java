package br.com.zupacademy.jonathan.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.zupacademy.jonathan.mercadolivre.categoria.Categoria;
import br.com.zupacademy.jonathan.mercadolivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zupacademy.jonathan.mercadolivre.usuario.Usuario;
import br.com.zupacademy.jonathan.mercadolivre.utils.validations.ExistsValue;
import br.com.zupacademy.jonathan.mercadolivre.utils.validations.UniqueValue;

public class ProdutoRequest {

	@NotBlank
	@UniqueValue(domainClass = Produto.class, fieldName = "nome", message = "Você já possui um produto cadastrado com esse nome")
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@Positive
	private Integer quantidade;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	@NotNull
	@ExistsValue(domainClass = Categoria.class, fieldName = "id", message = "Categoria informada não existe")
	private Long categoriaId;
	@Size(min = 3)
	@Valid
	private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();

	public ProdutoRequest(@NotBlank String nome, @Positive BigDecimal valor, @Positive Integer quantidade,
			@NotBlank @Length(max = 1000) String descricao, Long categoriaId,
			List<CaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.caracteristicas.addAll(caracteristicas);
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}
	
	public List<CaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	@Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", categoriaId=" + categoriaId +
                ", caracteristicas=" + caracteristicas +
                '}';
    }
	
	public Produto toModel(EntityManager manager, Usuario dono) {
		return new Produto(this.nome,
                this.valor,
                this.quantidade,
                this.descricao,
                manager.find(Categoria.class, categoriaId),
                dono,
                caracteristicas
                );
	}

	public Set<String> buscaCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultado = new HashSet<>();
		for(CaracteristicaRequest caracteristica : caracteristicas) {
			String nome = caracteristica.getNome();
			if(!nomesIguais.add(nome)) {
				resultado.add(nome);
			}
		}
		return resultado;
	}
}