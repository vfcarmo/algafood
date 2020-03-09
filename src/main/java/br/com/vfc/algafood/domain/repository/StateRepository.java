package br.com.vfc.algafood.domain.repository;

import br.com.vfc.algafood.domain.model.State;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CustomJpaRepository<State, Long> {
}
