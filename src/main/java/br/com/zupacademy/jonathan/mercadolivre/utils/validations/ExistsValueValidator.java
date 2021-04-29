package br.com.zupacademy.jonathan.mercadolivre.utils.validations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExistsValueValidator implements ConstraintValidator<ExistsValue, Object> {

    private String domainAttribute;
    private Class<?> klass;
    private boolean required;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsValue params) { 
    	domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(!required && value == null ) {
			return true;
		}
		Query query = manager.createQuery("select 1 from " +klass.getName() + " where "+ domainAttribute + "=:pValue");
        query.setParameter("pValue", value);
        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <= 1, "Você tem mais de um " + klass + " com o atributo " + domainAttribute + " = " + value);

        return !list.isEmpty();
	}
}