package br.com.zupacademy.jonathan.mercadolivre.utils.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsValueValidator.class)
@Documented
public @interface ExistsValue {
    String message() default "{unique.value.violation}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    String fieldName();
    Class<?> domainClass();
    boolean required() default true;
}