package br.com.vfc.algafood.domain.repository;

import br.com.vfc.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>, RestaurantRepositoryQueries,
        JpaSpecificationExecutor<Restaurant> {

    @Query("select distinct r from Restaurant r join fetch r.cookery join fetch r.address.city c join fetch c.state left join fetch r.paymentMethods")
    List<Restaurant> findAll();

    List<Restaurant> findByShippingFeeBetween(BigDecimal initialValue, BigDecimal finalValue);

    List<Restaurant> findByNameAndCookeryId(String name, Long cookeryId);
}
