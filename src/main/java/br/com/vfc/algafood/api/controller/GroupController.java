package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.GroupAssembler;
import br.com.vfc.algafood.api.model.GroupModel;
import br.com.vfc.algafood.api.model.input.GroupInput;
import br.com.vfc.algafood.domain.model.Group;
import br.com.vfc.algafood.domain.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    private GroupService groupService;

    private GroupAssembler groupAssembler;

    @Autowired
    public GroupController(GroupService groupService, GroupAssembler groupAssembler) {
        this.groupService = groupService;
        this.groupAssembler = groupAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {

        List<Group> groups = groupService.list();

        return ResponseEntity.ok(groupAssembler.toCollectionModel(groups));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {

        Group group = groupService.findById(id);

        return ResponseEntity.ok(groupAssembler.toModel(group));
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody GroupInput input) {

        Group group = groupService.save(groupAssembler.toDomain(input));

        GroupModel model = groupAssembler.toModel(group);

        return ResponseEntity
                .created(URI.create(String.format("/groups/%s", model.getId())))
                .body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody GroupInput input) {

        Group group = groupService.save(groupAssembler.toDomain(id, input));

        return ResponseEntity.ok(groupAssembler.toModel(group));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {

        groupService.remove(id);
    }
}
