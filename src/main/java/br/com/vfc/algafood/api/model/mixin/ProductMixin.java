package br.com.vfc.algafood.api.model.mixin;

import br.com.vfc.algafood.domain.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

public class ProductMixin {

    @JsonIgnore
    private Set<Restaurant> restaurants = new HashSet<>();
}
