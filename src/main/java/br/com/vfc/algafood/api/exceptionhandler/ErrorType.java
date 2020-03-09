package br.com.vfc.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ErrorType {
    VALIDATION_FAILED("Validation Failed", "/validation-failed"),
    ENTITY_CONFLICT("Entity Conflict", "/entity-conflict"),
    RESOURCE_NOT_FOUND("Resource not found", "/resource-not-found"),
    INVALID_REQUEST_PARAMETER("Invalid Request Parameter", "/invalid-request-parameter"),
    MESSAGE_NOT_READABLE("Message Not Readable", "/message-not-readable"),
    SERVER_ERROR("Internal Error", "/internal-error");

    private String title;

    private String uri;

    ErrorType(String title, String path) {
        this.title = title;
        this.uri = String.format("https://algafood.com%s", path);
    }
}
