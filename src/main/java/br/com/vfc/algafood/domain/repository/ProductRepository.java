package br.com.vfc.algafood.domain.repository;

import br.com.vfc.algafood.domain.model.Product;
import br.com.vfc.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CustomJpaRepository<Product, Long> {

    @Query("from Product where restaurant.id = :restaurantId and id = :productId")
    Optional<Product> findById(@Param("restaurantId") Long restaurantId, @Param("productId") Long productId);

    List<Product> findByRestaurant(Restaurant restaurant);
}
