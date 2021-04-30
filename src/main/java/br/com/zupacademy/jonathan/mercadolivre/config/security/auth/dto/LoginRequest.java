package br.com.zupacademy.jonathan.mercadolivre.config.security.auth.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {

	private String login;
	private String senha;

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(login, senha);
	}
}
