package br.com.vfc.algafood.api.exceptionhandler;

import br.com.vfc.algafood.domain.exception.BusinessException;
import br.com.vfc.algafood.domain.exception.EntityNotFoundException;
import br.com.vfc.algafood.domain.exception.UsedEntityException;
import br.com.vfc.algafood.domain.exception.ValidationException;
import br.com.vfc.algafood.util.StringUtils;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_SERVER_ERROR = "Unexpected internal error in the system. " +
            "Try again and if it continue, please enter in contact with the system's administrator.";

    private final MessageSource messageSource;

    @Autowired
    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String detail = String.format("Resource '%s' does not exist.", ex.getRequestURL());
        ErrorMessage error = createErrorBuilder((ErrorType.RESOURCE_NOT_FOUND), status, request, detail)
                .userMessage(MSG_SERVER_ERROR).build();

        return handleExceptionInternal(ex, error, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }
        return super.handleTypeMismatch(ex, headers, status, request);
    }


    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String detail = String.format("URL parameter '%s' received value '%s' which is an invalid type. " +
                        "The parameter must be a type of '%s'.",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
        ErrorMessage error = createErrorBuilder(ErrorType.INVALID_REQUEST_PARAMETER, status, request, detail)
                .userMessage(MSG_SERVER_ERROR).build();

        return handleExceptionInternal(ex, error, status, request);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }

        if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        String detail = "Invalid request body. Check the syntax request body.";
        ErrorMessage error = createErrorBuilder(ErrorType.MESSAGE_NOT_READABLE, status, request, detail)
                .userMessage(MSG_SERVER_ERROR).build();

        return handleExceptionInternal(ex, error, status, request);
    }

    protected ResponseEntity<Object> handlePropertyBindingException(
            PropertyBindingException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String fieldName = extractFieldNameFromPath(e.getPath());
        String detail = String.format("Field '%s' does not exist. Check or remove it and try again.", fieldName);
        ErrorMessage error = createErrorBuilder(ErrorType.MESSAGE_NOT_READABLE, status, request, detail)
                .userMessage(MSG_SERVER_ERROR).build();
        return handleExceptionInternal(e, error, status, request);
    }

    protected ResponseEntity<Object> handleInvalidFormatException(
            InvalidFormatException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String fieldName = extractFieldNameFromPath(e.getPath());
        Object fieldValue = e.getValue();
        String fieldType = e.getTargetType().getSimpleName();

        String detail = String.format("Field '%s' received value '%s' which is an invalid type. " +
                        "Please informe a value of the type %s.", fieldName, fieldValue,
                fieldType);
        ErrorMessage error = createErrorBuilder(ErrorType.MESSAGE_NOT_READABLE, status, request, detail)
                .userMessage(MSG_SERVER_ERROR).build();
        return handleExceptionInternal(e, error, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();

        return handleArgumentNotValid(ex, result, status, request);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidation(ValidationException ex, WebRequest request) {

        return handleArgumentNotValid(ex, ex.getBindingResult(), HttpStatus.BAD_REQUEST, request);
    }

    protected ResponseEntity<Object> handleArgumentNotValid(Exception ex,
            BindingResult bindingResult, HttpStatus status, WebRequest request) {

        List<ErrorMessage.Error> errors = bindingResult.getAllErrors()
                .stream()
                .map(objectError -> {
                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }
                    return ErrorMessage.Error.builder()
                            .name(name)
                            .userMessage(getMessage(
                                    objectError, request.getLocale()))
                            .build();
                }).collect(Collectors.toList());

        String detail = "One or more fields are invalid. Check their values and try again.";
        ErrorMessage error = createErrorBuilder(ErrorType.VALIDATION_FAILED, status, request, detail)
                .errors(errors).build();
        return handleExceptionInternal(ex, error, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException e, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorMessage error = createErrorBuilder(ErrorType.RESOURCE_NOT_FOUND, status, request, e.getMessage()).build();

        return handleExceptionInternal(e, error, status, request);
    }

    @ExceptionHandler(UsedEntityException.class)
    public ResponseEntity<?> handleUsedEntityException(UsedEntityException e, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ErrorMessage error = createErrorBuilder(ErrorType.ENTITY_CONFLICT, status, request, e.getMessage()).build();

        return handleExceptionInternal(e, error, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage error = createErrorBuilder(ErrorType.VALIDATION_FAILED, status, request, e.getMessage()).build();

        return handleExceptionInternal(e, error, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleInternalServerError(Exception e, WebRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorMessage error = createErrorBuilder(ErrorType.SERVER_ERROR, status, request, MSG_SERVER_ERROR)
                .userMessage(MSG_SERVER_ERROR).build();

        return handleExceptionInternal(e, error, status, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpStatus status, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = ErrorMessage.builder()
                    .status(status.value())
                    .type(buildTypeError(status))
                    .title(status.getReasonPhrase())
                    .detail(ex.getMessage())
                    .timestamp(OffsetDateTime.now())
                    .userMessage(ex.getMessage())
                    .build();
        } else if (body instanceof String) {
            body = ErrorMessage.builder()
                    .status(status.value())
                    .type(buildTypeError(status))
                    .title(status.getReasonPhrase())
                    .detail((String) body)
                    .timestamp(OffsetDateTime.now())
                    .userMessage((String) body)
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private String extractFieldNameFromPath(List<JsonMappingException.Reference> path) {
        return path.stream()
                .map(JsonMappingException.Reference::getFieldName).collect(Collectors.joining("."));
    }

    private String buildTypeError(HttpStatus status) {
        return String.format("https://algafood.com/%s", StringUtils.toKebabCase(status.getReasonPhrase()));
    }

    private ErrorMessage.ErrorMessageBuilder createErrorBuilder(
            ErrorType errorType, HttpStatus status, WebRequest request, String detail, Object... args) {
        return ErrorMessage.builder()
                .status(status.value())
                .type(errorType.getUri())
                .title(errorType.getTitle())
                .detail(getMessage(detail, request.getLocale(), args))
                .userMessage(getMessage(detail, request.getLocale(), args))
                .instance(getDecodeUrl(request))
                .timestamp(OffsetDateTime.now());
    }

    private String getDecodeUrl(WebRequest request) {
        String instance;
        try {
            instance = ((ServletWebRequest) request).getRequest().getRequestURI();
        } catch (Exception e) {
            instance = null;
        }
        return instance;
    }

    private String getMessage(ObjectError objectError, Locale locale) {
        String message;
        try {
            message = messageSource.getMessage(objectError, locale);
        } catch (Exception e) {
            message = null;
        }
        return message;
    }

    private String getMessage(String keyMessage, Locale locale, Object... args) {
        String message;
        try {
            message = messageSource.getMessage(keyMessage, args, locale);
        } catch (Exception e) {
            message = keyMessage;
        }
        return message;
    }
}
