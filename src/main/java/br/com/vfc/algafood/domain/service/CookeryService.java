package br.com.vfc.algafood.domain.service;

import br.com.vfc.algafood.domain.exception.EntityNotFoundException;
import br.com.vfc.algafood.domain.exception.UsedEntityException;
import br.com.vfc.algafood.domain.model.Cookery;
import br.com.vfc.algafood.domain.repository.CookeryRepository;
import br.com.vfc.algafood.infrastructure.notification.CreatedCookeryEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CookeryService implements CrudService<Cookery> {

    private ApplicationEventPublisher publisher;

    private CookeryRepository cookeryRepository;

    @Autowired
    public CookeryService(ApplicationEventPublisher publisher, CookeryRepository cookeryRepository) {
        this.publisher = publisher;
        this.cookeryRepository = cookeryRepository;
    }

    @Override
    public List<Cookery> list() {

        return cookeryRepository.findAll();
    }

    @Override
    public Cookery findById(Long id) {

        return cookeryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cookery with code %d does not exist.", id)));
    }

    @Transactional
    @Override
    public Cookery save(Cookery entity) {
        boolean newCookery = true;
        if (!entity.isNew()) {
            Cookery savedCookery = findById(entity.getId());
            BeanUtils.copyProperties(entity, savedCookery, "id", "createdDate");
            entity = savedCookery;
            newCookery = false;
        }
        Cookery cookery = cookeryRepository.save(entity);
        if (newCookery) {
            publisher.publishEvent(new CreatedCookeryEvent(cookery));
        }
        return cookery;
    }

    @Transactional
    @Override
    public void remove(Long id) {
        try {
            cookeryRepository.deleteById(id);
            cookeryRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Cookery with code %d does not exist.", id));
        } catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(
                    String.format("Cookery with code %d cannot be removed because it is in use.", id));
        }
    }
}
