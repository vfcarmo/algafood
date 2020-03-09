package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.OrderAssembler;
import br.com.vfc.algafood.api.model.OrderModel;
import br.com.vfc.algafood.api.model.input.OrderInput;
import br.com.vfc.algafood.domain.model.Order;
import br.com.vfc.algafood.domain.service.OrderService;
import br.com.vfc.algafood.util.ControllerUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("orders")
public class OrderController {

    private OrderService orderService;

    private OrderAssembler orderAssembler;

    private ObjectMapper objectMapper;

    private SmartValidator validator;

    @Autowired
    public OrderController(OrderService orderService, OrderAssembler orderAssembler,
                           ObjectMapper objectMapper, SmartValidator validator) {
        this.orderService = orderService;
        this.orderAssembler = orderAssembler;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @GetMapping
    public ResponseEntity<?> listar() {

        List<Order> orders = orderService.list();

        return ResponseEntity.ok(orderAssembler.toCollectionModel(orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {

        Order order = orderService.findById(id);

        return ResponseEntity.ok(orderAssembler.toModel(order));
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody OrderInput input) {

        Order order = orderService.save(orderAssembler.toDomain(input));

        OrderModel model = orderAssembler.toModel(order);

        return ResponseEntity
                .created(URI.create(String.format("/orders/%s", model.getId())))
                .body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody OrderInput input) {

        Order order = orderService.save(orderAssembler.toDomain(id, input));

        return ResponseEntity.ok(orderAssembler.toModel(order));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(
            @PathVariable Long id, @RequestBody Map<String, Object> fields, HttpServletRequest request) {

        Order order = orderService.findById(id);
        ControllerUtils.merge(objectMapper, fields, order, Order.class, request);
        ControllerUtils.validate(validator, order, "order");

        OrderInput input = new OrderInput();

        orderAssembler.copyProperties(order, input);
        return update(id, input);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {

        orderService.remove(id);
    }

}
