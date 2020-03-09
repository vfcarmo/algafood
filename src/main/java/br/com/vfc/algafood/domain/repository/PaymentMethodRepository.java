package br.com.vfc.algafood.domain.repository;

import br.com.vfc.algafood.domain.model.PaymentMethod;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends CustomJpaRepository<PaymentMethod, Long> {
}
