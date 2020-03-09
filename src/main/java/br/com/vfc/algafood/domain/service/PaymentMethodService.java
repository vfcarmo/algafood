package br.com.vfc.algafood.domain.service;

import br.com.vfc.algafood.domain.exception.EntityNotFoundException;
import br.com.vfc.algafood.domain.exception.UsedEntityException;
import br.com.vfc.algafood.domain.model.PaymentMethod;
import br.com.vfc.algafood.domain.repository.PaymentMethodRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentMethodService implements CrudService<PaymentMethod> {

    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public List<PaymentMethod> list() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod findById(Long id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Payment method with code %d does not exist.", id)));
    }

    @Transactional
    @Override
    public PaymentMethod save(PaymentMethod entity) {
        if (!entity.isNew()) {
            PaymentMethod savedEntity = findById(entity.getId());
            BeanUtils.copyProperties(entity, savedEntity, "id", "createdDate");
            entity = savedEntity;
        }
        return paymentMethodRepository.save(entity);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        try {
            paymentMethodRepository.deleteById(id);
            paymentMethodRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Payment method with code %d does not exist.", id));
        } catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(
                    String.format("Payment method with code %d cannot be removed because it is in use.", id));
        }
    }
}
