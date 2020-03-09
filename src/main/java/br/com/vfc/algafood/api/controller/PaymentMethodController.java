package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.PaymentMethodAssembler;
import br.com.vfc.algafood.api.model.PaymentMethodModel;
import br.com.vfc.algafood.api.model.input.PaymentMethodInput;
import br.com.vfc.algafood.domain.model.PaymentMethod;
import br.com.vfc.algafood.domain.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/payment-methods")
public class PaymentMethodController {

    private PaymentMethodService paymentMethodService;

    private PaymentMethodAssembler paymentMethodAssembler;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService, PaymentMethodAssembler paymentMethodAssembler) {
        this.paymentMethodService = paymentMethodService;
        this.paymentMethodAssembler = paymentMethodAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {

        List<PaymentMethod> paymentMethods = paymentMethodService.list();

        return ResponseEntity.ok(paymentMethodAssembler.toCollectionModel(paymentMethods));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {

        PaymentMethod paymentMethod = paymentMethodService.findById(id);

        return ResponseEntity.ok(paymentMethodAssembler.toModel(paymentMethod));
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody PaymentMethodInput input) {

        PaymentMethod paymentMethod = paymentMethodService.save(paymentMethodAssembler.toDomain(input));

        PaymentMethodModel model = paymentMethodAssembler.toModel(paymentMethod);

        return ResponseEntity
                .created(URI.create(String.format("/payment-methods/%s", model.getId())))
                .body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PaymentMethodInput input) {

        PaymentMethod paymentMethod = paymentMethodService.save(paymentMethodAssembler.toDomain(id, input));

        return ResponseEntity.ok(paymentMethodAssembler.toModel(paymentMethod));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {

        paymentMethodService.remove(id);
    }
}
