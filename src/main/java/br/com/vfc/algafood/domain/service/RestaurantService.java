package br.com.vfc.algafood.domain.service;

import br.com.vfc.algafood.domain.exception.BusinessException;
import br.com.vfc.algafood.domain.exception.EntityNotFoundException;
import br.com.vfc.algafood.domain.exception.UsedEntityException;
import br.com.vfc.algafood.domain.model.*;
import br.com.vfc.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService implements CrudService<Restaurant> {

    private RestaurantRepository restaurantRepository;

    private CookeryService cookeryService;

    private CityService cityService;

    private PaymentMethodService paymentMethodService;

    private UserService userService;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, CookeryService cookeryService,
                             CityService cityService, PaymentMethodService paymentMethodService,
                             UserService userService) {
        this.restaurantRepository = restaurantRepository;
        this.cookeryService = cookeryService;
        this.cityService = cityService;
        this.paymentMethodService = paymentMethodService;
        this.userService = userService;
    }

    @Override
    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long id) {

        return restaurantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Restaurant with code %d does not exist.", id)));
    }

    @Transactional
    @Override
    public Restaurant save(Restaurant entity) {

        if (entity.isNew()) {
            Restaurant savedRestaurant = findById(entity.getId());
            BeanUtils.copyProperties(entity, savedRestaurant, "id", "createdDate",
                    "paymentMethods", "products", "responsible");
            entity = savedRestaurant;
        }

        Long cookeryId = entity.getCookery().getId();
        try {
            Cookery savedCookery = cookeryService.findById(cookeryId);
            entity.setCookery(savedCookery);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }

        Long cityId = entity.getAddress().getCity().getId();
        try {
            City savedCity = cityService.findById(cityId);
            entity.getAddress().setCity(savedCity);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
        return restaurantRepository.save(entity);
    }

    @Transactional
    public void open(Long id) {
        Restaurant savedRestaurant = findById(id);

        savedRestaurant.open();
    }

    @Transactional
    public void close(Long id) {
        Restaurant savedRestaurant = findById(id);

        savedRestaurant.close();
    }

    @Transactional
    public void active(Long id) {
        Restaurant savedRestaurant = findById(id);

        savedRestaurant.active();
    }

    @Transactional
    public void inactive(Long id) {
        Restaurant savedRestaurant = findById(id);

        savedRestaurant.inactive();
    }

    @Transactional
    @Override
    public void remove(Long id) {
        try {
            restaurantRepository.deleteById(id);
            restaurantRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Restaurant with code %d does not exist.", id));
        } catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(
                    String.format("Restaurant with code %d cannot be removed because it is in use.", id));
        }
    }

    @Transactional
    public void addPaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = findById(restaurantId);
        try {
            PaymentMethod paymentMethod = paymentMethodService.findById(paymentMethodId);
            restaurant.addPaymentMethod(paymentMethod);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Transactional
    public void removePaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = findById(restaurantId);
        try {
            PaymentMethod paymentMethod = paymentMethodService.findById(paymentMethodId);
            restaurant.removePaymentMethod(paymentMethod);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Transactional
    public void addResponsible(Long restaurantId, Long userId) {
        Restaurant restaurant = findById(restaurantId);
        try {
            User responsible = userService.findById(userId);
            restaurant.addResponsible(responsible);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Transactional
    public void removeResponsible(Long restaurantId, Long userId) {
        Restaurant restaurant = findById(restaurantId);
        try {
            User responsible = userService.findById(userId);
            restaurant.removeResponsible(responsible);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Transactional
    public void active(List<Long> restaurantIds) {
        try {
            restaurantIds.forEach(this::active);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Transactional
    public void inactive(List<Long> restaurantIds) {
        try {
            restaurantIds.forEach(this::inactive);
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}

