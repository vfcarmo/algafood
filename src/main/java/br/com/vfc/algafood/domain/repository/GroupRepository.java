package br.com.vfc.algafood.domain.repository;

import br.com.vfc.algafood.domain.model.Group;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CustomJpaRepository<Group, Long> {
}
