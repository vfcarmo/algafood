package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.RestaurantAssembler;
import br.com.vfc.algafood.api.model.RestaurantModel;
import br.com.vfc.algafood.api.model.RestaurantWrapper;
import br.com.vfc.algafood.api.model.input.RestaurantInput;
import br.com.vfc.algafood.domain.model.Restaurant;
import br.com.vfc.algafood.domain.service.RestaurantService;
import br.com.vfc.algafood.util.ControllerUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

    private RestaurantService restaurantService;

    private RestaurantAssembler restaurantAssembler;

    private ObjectMapper objectMapper;

    private SmartValidator validator;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantAssembler restaurantAssembler,
                                ObjectMapper objectMapper, SmartValidator validator) {
        this.restaurantService = restaurantService;
        this.restaurantAssembler = restaurantAssembler;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {

        List<Restaurant> restaurants = restaurantService.list();

        return ResponseEntity.ok(restaurantAssembler.toCollectionModel(restaurants));
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> listXml() {

        List<Restaurant> restaurants = restaurantService.list();

        return ResponseEntity.ok(new RestaurantWrapper(restaurantAssembler.toCollectionModel(restaurants)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantModel> find(@PathVariable Long id) {

        Restaurant restaurant = restaurantService.findById(id);

        return ResponseEntity.ok(restaurantAssembler.toModel(restaurant));
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody RestaurantInput input) {

        Restaurant restaurant = restaurantService.save(restaurantAssembler.toDomain(input));

        RestaurantModel model = restaurantAssembler.toModel(restaurant);

        return ResponseEntity
                .created(URI.create(String.format("/restaurants/%s", model.getId())))
                .body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody RestaurantInput input) {

        Restaurant restaurant = restaurantService.save(restaurantAssembler.toDomain(id, input));

        return ResponseEntity.ok(restaurantAssembler.toModel(restaurant));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(
            @PathVariable Long id, @RequestBody Map<String, Object> fields, HttpServletRequest request) {

        Restaurant restaurant = restaurantService.findById(id);
        ControllerUtils.merge(objectMapper, fields, restaurant, Restaurant.class, request);
        ControllerUtils.validate(validator, restaurant, "restaurant");

        RestaurantInput input = new RestaurantInput();

        restaurantAssembler.copyProperties(restaurant, input);
        return update(id, input);
    }

    @PutMapping("/{id}/opening")
    public void opning(@PathVariable Long id) {

        restaurantService.open(id);
    }

    @PutMapping("/{id}/closing")
    public void closing(@PathVariable Long id) {

        restaurantService.close(id);
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void active(@PathVariable Long id) {

        restaurantService.active(id);
    }

    @DeleteMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactive(@PathVariable Long id) {

        restaurantService.inactive(id);
    }

    @PutMapping("/activation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateMany(@RequestBody List<Long> restaurantIds) {

        restaurantService.active(restaurantIds);
    }

    @DeleteMapping("/activation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivateMany(@RequestBody List<Long> restaurantIds) {

        restaurantService.inactive(restaurantIds);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {

        restaurantService.remove(id);
    }
}
