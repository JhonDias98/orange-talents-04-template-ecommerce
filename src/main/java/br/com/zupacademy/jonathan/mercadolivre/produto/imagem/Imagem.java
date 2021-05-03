package br.com.zupacademy.jonathan.mercadolivre.produto.imagem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import br.com.zupacademy.jonathan.mercadolivre.produto.Produto;

@Entity
public class Imagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@URL
	private String link;

	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;

	public Imagem(@NotNull @Valid Produto produto, @URL @NotBlank String link) {
        this.produto = produto;
        this.link = link;
    }
	
	@Deprecated
	public Imagem() {
		
	}

	public Long getId() {
		return id;
	}

	public String getLink() {
		return link;
	}

	public Produto getProduto() {
		return produto;
	}

	@Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }
}
