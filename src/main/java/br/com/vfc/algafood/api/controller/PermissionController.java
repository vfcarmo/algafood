package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.PermissionAssembler;
import br.com.vfc.algafood.api.model.PermissionModel;
import br.com.vfc.algafood.api.model.input.PermissionInput;
import br.com.vfc.algafood.domain.model.Permission;
import br.com.vfc.algafood.domain.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/permissions")
public class PermissionController {

    private PermissionService permissionService;

    private PermissionAssembler permissionAssembler;

    @Autowired
    public PermissionController(PermissionService permissionService, PermissionAssembler permissionAssembler) {
        this.permissionService = permissionService;
        this.permissionAssembler = permissionAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {

        List<Permission> permissions = permissionService.list();

        return ResponseEntity.ok(permissionAssembler.toCollectionModel(permissions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {

        Permission permission = permissionService.findById(id);

        return ResponseEntity.ok(permissionAssembler.toModel(permission));
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody PermissionInput input) {

        Permission permission = permissionService.save(permissionAssembler.toDomain(input));

        PermissionModel model = permissionAssembler.toModel(permission);

        return ResponseEntity
                .created(URI.create(String.format("/payment_methods/%s", model.getId())))
                .body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PermissionInput input) {

        Permission permission = permissionService.save(permissionAssembler.toDomain(id, input));

        return ResponseEntity.ok(permissionAssembler.toModel(permission));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {

        permissionService.remove(id);
    }
}
