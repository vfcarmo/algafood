package br.com.vfc.algafood.domain.service;

import br.com.vfc.algafood.domain.exception.BusinessException;
import br.com.vfc.algafood.domain.exception.EntityNotFoundException;
import br.com.vfc.algafood.domain.exception.UsedEntityException;
import br.com.vfc.algafood.domain.model.Group;
import br.com.vfc.algafood.domain.model.User;
import br.com.vfc.algafood.domain.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements CrudService<User> {

    private UserRepository userRepository;

    private GroupService groupService;

    @Autowired
    public UserService(UserRepository userRepository, GroupService groupService) {
        this.userRepository = userRepository;
        this.groupService = groupService;
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("User with code %d does not exist.", id)));
    }

    @Transactional
    @Override
    public User save(User entity) {
        if (!entity.isNew()) {
            User savedEntity = findById(entity.getId());
            BeanUtils.copyProperties(entity, savedEntity,
                    "id", "createdDate", "updatedDate", "password", "groups");
            entity = savedEntity;
        }
        Optional<User> savedUser = userRepository.findByEmail(entity.getEmail());

        if (savedUser.isPresent() && !savedUser.get().equals(entity)) {
            throw new BusinessException(String.format("Email (%s) is used by another user.", entity.getEmail()));
        }
        return userRepository.save(entity);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        try {
            userRepository.deleteById(id);
            userRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("User with code %d does not exist.", id));
        } catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(
                    String.format("User with code %d cannot be removed because it is in use.", id));
        }
    }

    @Transactional
    public void updatePassword(Long id, String password, String newPassword) {
        User savedUser = findById(id);

        if (!savedUser.getPassword().equals(password)) {
            throw new BusinessException("Current password is incorrect.");
        }
        savedUser.setPassword(newPassword);
    }

    @Transactional
    public void assignGroup(Long userId, Long groupId) {
        User user = findById(userId);
        try {
            Group group = groupService.findById(groupId);
            user.addGroup(group);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(String.format("Does not exist a group with id %d", groupId));
        }
    }

    @Transactional
    public void removeGroup(Long userId, Long groupId) {
        User user = findById(userId);
        try {
            Group group = groupService.findById(groupId);
            user.removeGroup(group);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(String.format("Does not exist a group with id %d", groupId));
        }
    }
}
