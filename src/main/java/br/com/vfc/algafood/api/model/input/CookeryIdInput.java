package br.com.vfc.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CookeryIdInput {

    @NotNull
    private Long id;
}
