package br.com.vfc.algafood.api.model.mixin;

import br.com.vfc.algafood.domain.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMixin {

    @JsonIgnoreProperties(value = { "name", "createdDate", "updatedDate" }, allowGetters = true)
    private Cookery cookery;

    @JsonIgnore
    private Address address;

    @JsonIgnore
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    @JsonIgnore
    private List<User> responsible = new ArrayList<>();
}
