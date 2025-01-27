package br.com.zupacademy.jonathan.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zupacademy.jonathan.mercadolivre.utils.validations.UniqueValue;

public class UsuarioRequest {

	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "login", message = "Já existe um usuário com esse e-mail/login")
	private String login;
	@NotBlank
	@Size(min = 6)
	private String senha;
	
	public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(login, new SenhaLimpa(senha));
	}
}
