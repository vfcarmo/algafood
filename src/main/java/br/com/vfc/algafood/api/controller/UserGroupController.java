package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.GroupAssembler;
import br.com.vfc.algafood.domain.model.User;
import br.com.vfc.algafood.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/{userId}/groups")
public class UserGroupController {

    private UserService userService;

    private GroupAssembler groupAssembler;

    @Autowired
    public UserGroupController(UserService userService, GroupAssembler groupAssembler) {
        this.userService = userService;
        this.groupAssembler = groupAssembler;
    }

    @GetMapping
    public ResponseEntity<?> list(@PathVariable Long userId) {

        User user = userService.findById(userId);

        return ResponseEntity.ok(groupAssembler.toCollectionModel(user.getGroups()));
    }

    @PutMapping("/{groupId}")
    public void assign(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.assignGroup(userId, groupId);
    }

    @DeleteMapping("/{groupId}")
    public void remove(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.removeGroup(userId, groupId);
    }
}
