package br.com.zupacademy.jonathan.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.jonathan.mercadolivre.utils.validations.ExistsValue;
import br.com.zupacademy.jonathan.mercadolivre.utils.validations.UniqueValue;

public class CategoriaRequest {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Já existe uma categoria com esse nome")
	private String nome;
	
	@ExistsValue(domainClass = Categoria.class, fieldName = "id", required = false, message = "Categoria mãe não existe")
	private Long categoriaId;

	@JsonCreator
	public CategoriaRequest(@NotBlank String nome) {
		this.nome = nome;
	}

	public CategoriaRequest(@NotBlank String nome, Long categoriaId) {
		this.nome = nome;
		this.categoriaId = categoriaId;
	}

	public String getNome() {
		return nome;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}
	
	public Categoria toModel(EntityManager manager) {
		Categoria categoria = new Categoria(nome);
		if(categoriaId != null) {
			categoria.setCategoria(manager.find(Categoria.class, categoriaId));
		}
		return categoria;
	}
}
