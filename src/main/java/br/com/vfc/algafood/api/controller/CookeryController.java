package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.CookeryAssembler;
import br.com.vfc.algafood.api.model.CookeryModel;
import br.com.vfc.algafood.api.model.CookeryWrapper;
import br.com.vfc.algafood.api.model.input.CookeryInput;
import br.com.vfc.algafood.domain.model.Cookery;
import br.com.vfc.algafood.domain.service.CookeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cookeries",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CookeryController {

    private CookeryService cookeryService;

    private CookeryAssembler cookeryAssembler;

    @Autowired
    public CookeryController(CookeryService cookeryService, CookeryAssembler cookeryAssembler) {
        this.cookeryService = cookeryService;
        this.cookeryAssembler = cookeryAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {

        List<Cookery> cookeries = cookeryService.list();

        return ResponseEntity.ok(cookeryAssembler.toCollectionModel(cookeries));
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<CookeryWrapper> listXml() {

        List<Cookery> cookeries = cookeryService.list();

        return ResponseEntity.ok(new CookeryWrapper(cookeryAssembler.toCollectionModel(cookeries)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {

        Cookery cookery = cookeryService.findById(id);

        CookeryModel model = cookeryAssembler.toModel(cookery);

        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody CookeryInput input) {

        Cookery cookery = cookeryService.save(cookeryAssembler.toDomain(input));

        CookeryModel model = cookeryAssembler.toModel(cookery);

        return ResponseEntity
                .created(URI.create(String.format("/cookeries/%s", model.getId())))
                .body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CookeryInput input) {

        Cookery cookery = cookeryService.save(cookeryAssembler.toDomain(id, input));

        return ResponseEntity.ok(cookeryAssembler.toModel(cookery));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {

        cookeryService.remove(id);
    }
}
