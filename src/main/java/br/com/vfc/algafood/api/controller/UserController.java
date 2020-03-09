package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.UserAssembler;
import br.com.vfc.algafood.api.model.UserModel;
import br.com.vfc.algafood.api.model.input.UserInput;
import br.com.vfc.algafood.api.model.input.UserPasswordInput;
import br.com.vfc.algafood.api.model.input.UserUpdateInput;
import br.com.vfc.algafood.domain.model.User;
import br.com.vfc.algafood.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    private UserAssembler userAssembler;

    @Autowired
    public UserController(UserService userService, UserAssembler userAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {

        List<User> users = userService.list();

        return ResponseEntity.ok(userAssembler.toCollectionModel(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {

        User user = userService.findById(id);

        return ResponseEntity.ok(userAssembler.toModel(user));
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody UserInput input) {

        User user = userService.save(userAssembler.toDomain(input));

        UserModel model = userAssembler.toModel(user);

        return ResponseEntity
                .created(URI.create(String.format("/users/%s", model.getId())))
                .body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UserUpdateInput input) {

        User user = userService.save(userAssembler.toDomain(id, input));

        return ResponseEntity.ok(userAssembler.toModel(user));
    }

    @PutMapping("/{id}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@PathVariable Long id, @Valid @RequestBody UserPasswordInput input) {

        userService.updatePassword(id, input.getPassword(), input.getNewPassword());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {

        userService.remove(id);
    }
}
