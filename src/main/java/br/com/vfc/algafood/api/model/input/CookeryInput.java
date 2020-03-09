package br.com.vfc.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CookeryInput {

    @NotBlank
    private String name;
}
