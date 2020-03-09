package br.com.vfc.algafood.domain.repository;

import br.com.vfc.algafood.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {

    List<Restaurant> find(String name, BigDecimal initialValue, BigDecimal finalValue);

    List<Restaurant> findUsingCriteria(String name, BigDecimal initialValue, BigDecimal finalValue);

    List<Restaurant> findByFreeShippingFeeUsingSpecs(String nome);
}
