package br.com.vfc.algafood.domain.repository;

import br.com.vfc.algafood.domain.model.Cookery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CookeryRepository extends CustomJpaRepository<Cookery, Long> {

    List<Cookery> findByNameContaining(String name);
}
