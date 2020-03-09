package br.com.vfc.algafood.domain.repository;

import br.com.vfc.algafood.domain.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CustomJpaRepository<Order, Long> {
}
