package br.com.vfc.algafood.infrastructure.repository.spec;

import br.com.vfc.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {

    public static Specification<Restaurant> freeShippingFee() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("shippingFee"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> similarName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
}
