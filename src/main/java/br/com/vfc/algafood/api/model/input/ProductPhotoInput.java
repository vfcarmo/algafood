package br.com.vfc.algafood.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductPhotoInput {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String contentType;

    @NotNull
    private Long length;
}
