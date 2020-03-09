package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.StateAssembler;
import br.com.vfc.algafood.api.model.StateModel;
import br.com.vfc.algafood.api.model.StateWrapper;
import br.com.vfc.algafood.api.model.input.StateInput;
import br.com.vfc.algafood.domain.model.State;
import br.com.vfc.algafood.domain.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/states",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class StateController {

    private StateService stateService;

    private StateAssembler stateAssembler;

    @Autowired
    public StateController(StateService stateService, StateAssembler stateAssembler) {
        this.stateService = stateService;
        this.stateAssembler = stateAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {

        List<State> states = stateService.list();

        return ResponseEntity.ok(stateAssembler.toCollectionModel(states));
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<StateWrapper> listXml() {

        List<State> states = stateService.list();

        return ResponseEntity.ok(new StateWrapper(stateAssembler.toCollectionModel(states)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateModel> find(@PathVariable Long id) {

        State state = stateService.findById(id);

        return ResponseEntity.ok(stateAssembler.toModel(state));
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody StateInput input) {

        State state = stateService.save(stateAssembler.toDomain(input));

        StateModel model = stateAssembler.toModel(state);

        return ResponseEntity.created(
                URI.create(String.format("/states/%s", model.getId())))
                .body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody StateInput input) {

        State state = stateService.save(stateAssembler.toDomain(id, input));

        return ResponseEntity.ok(stateAssembler.toModel(state));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {

        stateService.remove(id);
    }
}
