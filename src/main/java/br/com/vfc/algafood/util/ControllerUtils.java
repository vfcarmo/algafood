package br.com.vfc.algafood.util;

import br.com.vfc.algafood.domain.exception.ValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

public class ControllerUtils {

    public static <T> void merge(ObjectMapper objectMapper, Map<String, Object> fields,
                                 T object, Class<T> objectClass, HttpServletRequest request) {
        try {
            T convertedObject = objectMapper.convertValue(fields, objectClass);
            fields.forEach((name, rawValue) -> {
                String fieldName = StringUtils.toCamelCase(name);
                Field field = ReflectionUtils.findField(objectClass, fieldName);

                if (field != null) {
                    field.setAccessible(true);
                    Object value = ReflectionUtils.getField(field, convertedObject);
                    ReflectionUtils.setField(field, object, value);
                }
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, new ServletServerHttpRequest(request));
        }
    }

    public static void validate(SmartValidator validator, Object object, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(object, objectName);
        validator.validate(object, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
    }
}
