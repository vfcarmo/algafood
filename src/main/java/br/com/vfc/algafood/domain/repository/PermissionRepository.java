package br.com.vfc.algafood.domain.repository;

import br.com.vfc.algafood.domain.model.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CustomJpaRepository<Permission, Long> {
}
