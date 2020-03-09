package br.com.vfc.algafood.domain.repository;

import br.com.vfc.algafood.domain.model.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CustomJpaRepository<City, Long> {
}
