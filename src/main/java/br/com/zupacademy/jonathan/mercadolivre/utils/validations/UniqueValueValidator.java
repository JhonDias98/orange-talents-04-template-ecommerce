package br.com.zupacademy.jonathan.mercadolivre.utils.validations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object>{

	private String domainAttribute;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(UniqueValue params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		//Aqui é construido a query para consultar os dados
		Query query = manager.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + domainAttribute + "=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		return list.isEmpty();
	}
}