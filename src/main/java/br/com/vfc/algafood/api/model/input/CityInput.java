package br.com.vfc.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CityInput {

    @NotBlank
    private String name;

    @Valid
    @NotNull
    private StateIdInput state;
}
