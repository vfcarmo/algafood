package br.com.vfc.algafood.domain.service;

import br.com.vfc.algafood.domain.exception.EntityNotFoundException;
import br.com.vfc.algafood.domain.exception.UsedEntityException;
import br.com.vfc.algafood.domain.model.State;
import br.com.vfc.algafood.domain.repository.StateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StateService implements CrudService<State> {

    private StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public List<State> list() {

        return stateRepository.findAll();
    }

    @Override
    public State findById(Long id) {

        return stateRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("State with code %d does not exist.", id)));
    }

    @Transactional
    @Override
    public State save(State entity) {

        if (entity.isNew()) {
            State savedState = findById(entity.getId());
            BeanUtils.copyProperties(entity, savedState, "id", "createdDate");
            entity = savedState;
        }
        return stateRepository.save(entity);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        try {
            stateRepository.deleteById(id);
            stateRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("State with code %d does not exist.", id));
        } catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(
                    String.format("State with code %d cannot be removed because it is in use.", id));
        }
    }
}
