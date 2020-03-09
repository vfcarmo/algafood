package br.com.vfc.algafood.domain.service;

import br.com.vfc.algafood.domain.exception.EntityNotFoundException;
import br.com.vfc.algafood.domain.exception.UsedEntityException;
import br.com.vfc.algafood.domain.model.Order;
import br.com.vfc.algafood.domain.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService implements CrudService<Order> {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> list() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Order with code %d does not exist.", id)));
    }

    @Transactional
    @Override
    public Order save(Order entity) {
        if (!entity.isNew()) {
            Order savedOrder = findById(entity.getId());
            BeanUtils.copyProperties(entity, savedOrder, "id", "createdDate");
            entity = savedOrder;
        }
        return orderRepository.save(entity);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        try {
            orderRepository.deleteById(id);
            orderRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Restaurant with code %d does not exist.", id));
        } catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(
                    String.format("Restaurant with code %d cannot be removed because it is in use.", id));
        }
    }
}
