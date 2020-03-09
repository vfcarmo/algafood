package br.com.vfc.algafood.core.validation;

import br.com.vfc.algafood.domain.model.OrderStatus;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.time.OffsetDateTime;

public class ValidDateByOrderStatusValidator implements ConstraintValidator<ValidDateByOrderStatus, Object> {

    private String statusField;

    private String deliveryField;

    private String confirmationField;

    private String cancellationField;

    @Override
    public void initialize(ValidDateByOrderStatus constraintAnnotation) {
        this.statusField = constraintAnnotation.statusField();
        this.deliveryField = constraintAnnotation.deliveryField();
        this.confirmationField = constraintAnnotation.confirmationField();
        this.cancellationField = constraintAnnotation.cancellationField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            OrderStatus status = getValue(value, statusField, OrderStatus.class);

            switch (status) {
                case CONFIRMED:
                    OffsetDateTime confirmationDate = getValue(value, confirmationField, OffsetDateTime.class);
                    valid = confirmationDate != null;
                    break;
                case CANCELED:
                    OffsetDateTime cancellationDate = getValue(value, cancellationField, OffsetDateTime.class);
                    valid = cancellationDate != null;
                    break;
                default:
                    OffsetDateTime deliveryDate = getValue(value, deliveryField, OffsetDateTime.class);
                    valid = deliveryDate != null;
                    break;
            }
        } catch (Exception e) {
            throw new ValidationException(e);
        }
        return valid;
    }

    private <T> T getValue(Object object, String fieldName, Class<T> clazz) {
        try {
            return clazz.cast(BeanUtils.getPropertyDescriptor(object.getClass(), fieldName).getReadMethod().invoke(object));
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }
}
