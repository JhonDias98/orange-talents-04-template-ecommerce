package br.com.zupacademy.jonathan.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.zupacademy.jonathan.mercadolivre.categoria.Categoria;
import br.com.zupacademy.jonathan.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.jonathan.mercadolivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zupacademy.jonathan.mercadolivre.produto.imagem.Imagem;
import br.com.zupacademy.jonathan.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
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
	@CreationTimestamp
	private LocalDateTime instanteCadastro = LocalDateTime.now();
	@NotNull
	@ManyToOne
	private Categoria categoria;
	@NotNull
	@ManyToOne
	private Usuario dono;
	@Size(min = 3)
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	//cascade = CascadeType.PERSIST: sempre que cadastrar um produto, pode registrar as características junto
	private Set<Caracteristica> caracteristicas = new HashSet<>();
	//cascade = CascadeType.MERGE: atualiza ambos
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Imagem> imagens = new HashSet<>();

	public Produto(@NotBlank String nome, @Positive BigDecimal valor, @Positive Integer quantidade,
			@NotBlank @Length(max = 1000) String descricao, Categoria categoria, Usuario dono, 
			@Size(min = 3) @Valid Collection<CaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.dono = dono;
		this.caracteristicas.addAll(caracteristicas.stream()
				.map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
		
		Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no minomo 3 características");
	}
	
	@Deprecated
	public Produto() {
		
	}

	public Long getId() {
		return id;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getDono() {
		return dono;
	}

	public LocalDateTime getInstanteCadastro() {
		return instanteCadastro;
	}

	public void associaImagens(Set<String> links) {
		Set<Imagem> imagens = links.stream().map(link -> new Imagem(this, link)).collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public boolean pertenceAoUsuario(Usuario possivelDono) {
		return this.dono.equals(possivelDono);
	}

}