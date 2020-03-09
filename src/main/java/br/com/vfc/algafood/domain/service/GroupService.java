package br.com.vfc.algafood.domain.service;

import br.com.vfc.algafood.domain.exception.BusinessException;
import br.com.vfc.algafood.domain.exception.EntityNotFoundException;
import br.com.vfc.algafood.domain.exception.UsedEntityException;
import br.com.vfc.algafood.domain.model.Group;
import br.com.vfc.algafood.domain.model.Permission;
import br.com.vfc.algafood.domain.repository.GroupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupService implements CrudService<Group> {

    private GroupRepository groupRepository;

    private PermissionService permissionService;

    @Autowired
    public GroupService(GroupRepository groupRepository, PermissionService permissionService) {
        this.groupRepository = groupRepository;
        this.permissionService = permissionService;
    }

    @Override
    public List<Group> list() {
        return groupRepository.findAll();
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Group with code %d does not exist.", id)));
    }

    @Transactional
    @Override
    public Group save(Group entity) {
        if (!entity.isNew()) {
            Group savedEntity = findById(entity.getId());
            BeanUtils.copyProperties(entity, savedEntity, "id", "createdDate");
            entity = savedEntity;
        }
        return groupRepository.save(entity);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        try {
            groupRepository.deleteById(id);
            groupRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Group with code %d does not exist.", id));
        } catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(
                    String.format("Group with code %d cannot be removed because it is in use.", id));
        }
    }

    @Transactional
    public void assignPermission(Long groupId, Long permissionId) {
        Group group = findById(groupId);
        try {
            Permission permission = permissionService.findById(permissionId);
            group.addPermission(permission);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(String.format("Does not exist a permission with id %d", permissionId));
        }
    }

    @Transactional
    public void removePermission(Long groupId, Long permissionId) {
        Group group = findById(groupId);
        try {
            Permission permission = permissionService.findById(permissionId);
            group.removePermission(permission);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(String.format("Does not exist a permission with id %d", permissionId));
        }
    }

}
