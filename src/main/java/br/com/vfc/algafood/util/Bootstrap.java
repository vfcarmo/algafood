package br.com.vfc.algafood.util;

import br.com.vfc.algafood.domain.model.*;
import br.com.vfc.algafood.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Component
public class Bootstrap implements CommandLineRunner {

    private StateRepository stateRepository;
    private CityRepository cityRepository;
    private CookeryRepository cookeryRepository;
    private RestaurantRepository restaurantRepository;
    private PaymentMethodRepository paymentMethodRepository;
    private ProductRepository productRepository;

    @Autowired
    public Bootstrap(StateRepository stateRepository, CityRepository cityRepository,
                     CookeryRepository cookeryRepository, RestaurantRepository restaurantRepository,
                     PaymentMethodRepository paymentMethodRepository, ProductRepository productRepository) {
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.cookeryRepository = cookeryRepository;
        this.restaurantRepository = restaurantRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (shouldPerform()) {
            stateRepository.save(buildSimpleState());
            cityRepository.save(buildSimpleCity());
            cookeryRepository.save(buildSimpleCookery());
            restaurantRepository.save(buildSimpleRestaurant());
            paymentMethodRepository.save(buildSimplePaymentMethod());
            productRepository.save(buildSimpleProduct());
        }
    }

    private boolean shouldPerform() {
        //TODO Substitute this for value read from parameter
        return false;
    }

    public static Product buildSimpleProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Coke");
        product.setDescription("Coke Energetic Drink");
        product.setValue(new BigDecimal("1.20"));
        ProductPhoto photo = buildSimpleProductPhoto();
        photo.setProduct(product);
        product.setPhoto(photo);
        return product;
    }

    public static ProductPhoto buildSimpleProductPhoto() {
        ProductPhoto photo = new ProductPhoto();
        photo.setId(1L);
        photo.setName("Coke.png");
        photo.setDescription("Can of Coke");
        photo.setContentType("image/png");
        photo.setLength(100L);
        return photo;
    }

    public static PaymentMethod buildSimplePaymentMethod() {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(1L);
        paymentMethod.setName("Credit Card");
        return paymentMethod;
    }

    public static Restaurant buildSimpleRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Ki Muqueca");
        restaurant.setAddress(buildSimpleAddress());
        restaurant.setShippingFee(new BigDecimal("10.50"));
        restaurant.setCookery(buildSimpleCookery());
        return restaurant;
    }

    public static Cookery buildSimpleCookery() {
        Cookery cookery = new Cookery();
        cookery.setId(1L);
        cookery.setName("Baiana");
        cookery.setCreatedDate(OffsetDateTime.now());
        return cookery;
    }

    private static Address buildSimpleAddress() {
        Address address = new Address();
        address.setCep("41650010");
        address.setStreet("Praia de Armação");
        address.setNumber("S/N");
        address.setDistrict("Armação");
        address.setCity(buildSimpleCity());
        return address;
    }

    public static City buildSimpleCity() {
        City city = new City();
        city.setId(1L);
        city.setName("Salvador");
        city.setState(buildSimpleState());
        city.setCreatedDate(OffsetDateTime.now());
        return city;
    }

    public static State buildSimpleState() {
        State state = new State();
        state.setId(1L);
        state.setName("Bahia");
        state.setCreatedDate(OffsetDateTime.now());
        return state;
    }
}
