package br.com.vfc.algafood.domain.service;

import br.com.vfc.algafood.domain.exception.EntityNotFoundException;
import br.com.vfc.algafood.domain.exception.UsedEntityException;
import br.com.vfc.algafood.domain.model.Permission;
import br.com.vfc.algafood.domain.repository.PermissionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionService implements CrudService<Permission> {

    private PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> list() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Permission with code %d does not exist.", id)));
    }

    @Transactional
    @Override
    public Permission save(Permission entity) {
        if (!entity.isNew()) {
            Permission savedEntity = findById(entity.getId());
            BeanUtils.copyProperties(entity, savedEntity, "id", "createdDate");
            entity = savedEntity;
        }
        return permissionRepository.save(entity);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        try {
            permissionRepository.deleteById(id);
            permissionRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Permission with code %d does not exist.", id));
        } catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(
                    String.format("Permission with code %d cannot be removed because it is in use.", id));
        }
    }
}
