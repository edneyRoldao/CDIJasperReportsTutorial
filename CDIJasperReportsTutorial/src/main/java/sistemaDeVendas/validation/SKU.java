package sistemaDeVendas.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "([a-zA-Z]{2}\\d{4,18})?")
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface SKU {
	
	//Este override atribui um valor padrão a mensagem que é definida no Pattern
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "{sistemaDeVendas.constraint.sku.message}"; // foi aplicado uma chave que vai fazer um bind no ValidationMessages.properties
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	

}
