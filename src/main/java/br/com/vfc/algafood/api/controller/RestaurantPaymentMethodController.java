package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.PaymentMethodAssembler;
import br.com.vfc.algafood.domain.model.Restaurant;
import br.com.vfc.algafood.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurants/{restaurantId}/payment-methods")
public class RestaurantPaymentMethodController {

    private RestaurantService restaurantService;

    private PaymentMethodAssembler paymentMethodAssembler;

    @Autowired
    public RestaurantPaymentMethodController(RestaurantService restaurantService, PaymentMethodAssembler paymentMethodAssembler) {
        this.restaurantService = restaurantService;
        this.paymentMethodAssembler = paymentMethodAssembler;
    }

    @GetMapping
    public ResponseEntity<?> list(@PathVariable Long restaurantId) {

        Restaurant restaurant = restaurantService.findById(restaurantId);

        return ResponseEntity.ok(paymentMethodAssembler.toCollectionModel(restaurant.getPaymentMethods()));
    }

    @PutMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPaymentMethod(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {

        restaurantService.addPaymentMethod(restaurantId, paymentMethodId);
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePaymentMethod(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {

        restaurantService.removePaymentMethod(restaurantId, paymentMethodId);
    }

}
