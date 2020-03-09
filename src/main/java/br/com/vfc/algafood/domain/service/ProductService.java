package br.com.vfc.algafood.domain.service;

import br.com.vfc.algafood.domain.exception.EntityNotFoundException;
import br.com.vfc.algafood.domain.exception.UsedEntityException;
import br.com.vfc.algafood.domain.model.Product;
import br.com.vfc.algafood.domain.model.Restaurant;
import br.com.vfc.algafood.domain.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private RestaurantService restaurantService;

    @Autowired
    public ProductService(ProductRepository productRepository, RestaurantService restaurantService) {
        this.productRepository = productRepository;
        this.restaurantService = restaurantService;
    }

    public List<Product> findByRestaurant(Long restaurantId) {

        Restaurant restaurant = restaurantService.findById(restaurantId);

        return productRepository.findByRestaurant(restaurant);
    }

    public Product findById(Long restaurantId, Long productId) {
        return productRepository.findById(restaurantId, productId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Product with code %s does not exist in restaurant %s", productId, restaurantId))
                );
    }

    @Transactional
    public Product save(Long restaurantId, Product product) {

        Restaurant restaurant = restaurantService.findById(restaurantId);

        Long productId = product.getId();
        if (productId != null) {
            Product savedProduct = findById(restaurantId, productId);
            BeanUtils.copyProperties(product, savedProduct, "id", "createdDate");
            product = savedProduct;
        }
        product.setRestaurant(restaurant);
        return productRepository.save(product);
    }

    @Transactional
    public void remove(Long restaurantId, Long productId) {
        try {
            Product product = findById(restaurantId, productId);
            productRepository.deleteById(product.getId());
            productRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Product with code %s does not exist in restaurant %s", productId, restaurantId));
        } catch (DataIntegrityViolationException e) {
            throw new UsedEntityException(
                    String.format("Product with code %d cannot be removed because it is in use.", productId));
        }
    }

}
