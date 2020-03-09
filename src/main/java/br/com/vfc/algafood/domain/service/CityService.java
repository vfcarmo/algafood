package br.com.vfc.algafood.domain.service;

import br.com.vfc.algafood.domain.exception.BusinessException;
import br.com.vfc.algafood.domain.exception.EntityNotFoundException;
import br.com.vfc.algafood.domain.exception.UsedEntityException;
import br.com.vfc.algafood.domain.model.City;
import br.com.vfc.algafood.domain.model.State;
import br.com.vfc.algafood.domain.repository.CityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService implements CrudService<City> {

    private CityRepository cityRepository;

    private StateService stateService;

    @Autowired
    public CityService(CityRepository cityRepository, StateService stateService) {
        this.cityRepository = cityRepository;
        this.stateService = stateService;
    }

    @Override
    public List<City> list() {

        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {

        return cityRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("City with code %d does not exist.", id)));
    }

    @Transactional
    @Override
    public City save(City entity) {

        if (!entity.isNew()) {
            City savedCity = findById(entity.getId());
            BeanUtils.copyProperties(entity, savedCity, "id", "createdDate");
            entity = savedCity;
        }

        Long stateId = entity.getState().getId();

        try {
            State savedState = stateService.findById(stateId);
            entity.setState(savedState);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(String.format("Does not exist a state with id %d", stateId), e);
        }
        return cityRepository.save(entity);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        try {
            cityRepository.deleteById(id);
            cityRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("City with code %d does not exist.", id));
        } catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(
                    String.format("City with code %d cannot be removed because it is in use.", id));
        }
    }
}
