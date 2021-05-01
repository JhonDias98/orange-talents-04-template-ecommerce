package br.com.zupacademy.jonathan.mercadolivre.produto.caracteristica;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.jonathan.mercadolivre.produto.ProdutoRequest;

public class ProibeCaracteristicasComNomeIgualValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}

		ProdutoRequest request = (ProdutoRequest) target;
		Set<String> nomesIguais = request.buscaCaracteristicasIguais();
		if(!nomesIguais.isEmpty()) {
			errors.rejectValue("caracteristicas", null, "Você tem caracteristicas iguais " + nomesIguais);
		}
	}

}
