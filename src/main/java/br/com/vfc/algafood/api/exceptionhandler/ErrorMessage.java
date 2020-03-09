package br.com.vfc.algafood.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
public class ErrorMessage {

    private Integer status;

    private OffsetDateTime timestamp;

    private String type;

    private String title;

    private String detail;

    private String instance;

    private String userMessage;

    private List<Error> errors;

    @Getter
    @Builder
    public static class Error {

        private String name;

        private String userMessage;
    }

}
