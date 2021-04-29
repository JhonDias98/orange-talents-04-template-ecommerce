package br.com.zupacademy.jonathan.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Email
	private String login;
	@NotBlank
	@Size(min = 6)
	private String senha;
	@NotNull
	@CreationTimestamp
	private LocalDateTime instanteCadastro = LocalDateTime.now();
	
	/**
	 * 
	 * @param login string em formato de email
	 * @param senha string com texto limpo
	 */
	public Usuario(@NotBlank @Email String login, @NotNull @Valid SenhaLimpa senhaLimpa) {
		Assert.isTrue(StringUtils.hasLength(login), "login não pode ser em branco");
		Assert.notNull(senhaLimpa, "o objeto do tipo senha limpa não pode ser nulo");
		this.login = login;
		this.senha = senhaLimpa.hash();
	}

	@Deprecated
	public Usuario() {}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getInstanteCadastro() {
		return instanteCadastro;
	}
}