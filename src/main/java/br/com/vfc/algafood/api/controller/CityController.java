package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.CityAssembler;
import br.com.vfc.algafood.api.model.CityModel;
import br.com.vfc.algafood.api.model.CityWrapper;
import br.com.vfc.algafood.api.model.input.CityInput;
import br.com.vfc.algafood.domain.model.City;
import br.com.vfc.algafood.domain.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cities",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CityController {

    private CityService cityService;

    private CityAssembler cityAssembler;

    @Autowired
    public CityController(CityService cityService, CityAssembler cityAssembler) {
        this.cityService = cityService;
        this.cityAssembler = cityAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {

        List<City> cities = cityService.list();

        return ResponseEntity.ok(cityAssembler.toCollectionModel(cities));
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<CityWrapper> listXml() {

        List<City> cities = cityService.list();

        return ResponseEntity.ok(new CityWrapper(cityAssembler.toCollectionModel(cities)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {

        City city = cityService.findById(id);

        return ResponseEntity.ok(cityAssembler.toModel(city));
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody CityInput input) {

        City city = cityService.save(cityAssembler.toDomain(input));

        CityModel model = cityAssembler.toModel(city);

        return ResponseEntity
                .created(URI.create(String.format("/cities/%s", model.getId())))
                .body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CityInput input) {

        City city = cityService.save(cityAssembler.toDomain(id, input));

        return ResponseEntity.ok(cityAssembler.toModel(city));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {

        cityService.remove(id);
    }

}
