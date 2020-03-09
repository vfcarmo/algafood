package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.UserAssembler;
import br.com.vfc.algafood.domain.model.Restaurant;
import br.com.vfc.algafood.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurants/{restaurantId}/responsible")
public class RestaurantResponsibleController {

    private RestaurantService restaurantService;

    private UserAssembler userAssembler;

    @Autowired
    public RestaurantResponsibleController(RestaurantService restaurantService, UserAssembler userAssembler) {
        this.restaurantService = restaurantService;
        this.userAssembler = userAssembler;
    }

    @GetMapping
    public ResponseEntity<?> list(@PathVariable Long restaurantId) {

        Restaurant restaurant = restaurantService.findById(restaurantId);

        return ResponseEntity.ok(userAssembler.toCollectionModel(restaurant.getResponsible()));
    }

    @PutMapping("/{userId}")
    public void assign(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.addResponsible(restaurantId, userId);
    }

    @DeleteMapping("/{userId}")
    public void remove(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.removeResponsible(restaurantId, userId);
    }
}
