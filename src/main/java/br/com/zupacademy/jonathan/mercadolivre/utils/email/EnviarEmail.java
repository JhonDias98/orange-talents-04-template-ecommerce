package br.com.zupacademy.jonathan.mercadolivre.utils.email;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.jonathan.mercadolivre.produto.pergunta.Pergunta;

@Component
public class EnviarEmail {

	@Autowired
	private Mailer mailer;

	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		mailer.send("<html>...</html>","Nova pergunta...",pergunta.getUsuario().getLogin(),"novapergunta@nossomercadolivre.com",pergunta.getUsuario().getLogin());
	}
}
