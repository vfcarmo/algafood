package br.com.vfc.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantModel {

    private Long id;

    private String name;

    private BigDecimal shippingFee;

    private CookeryModel cookery;

    private Boolean open;

    private Boolean active;

    private AddressModel address;
}
