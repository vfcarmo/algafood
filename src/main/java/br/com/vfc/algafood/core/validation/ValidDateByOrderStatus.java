package br.com.vfc.algafood.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = ValidDateByOrderStatusValidator.class)
public @interface ValidDateByOrderStatus {

    String message() default "{ValidDateByOrderStatus.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String statusField();

    String deliveryField();

    String confirmationField();

    String cancellationField();
}
