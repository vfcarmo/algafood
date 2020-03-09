package br.com.vfc.algafood.api.model.mixin;

import br.com.vfc.algafood.domain.model.State;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CityMixin {

    @JsonIgnoreProperties(value = { "name" }, allowGetters = true)
    private State state;
}
