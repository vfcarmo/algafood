package br.com.vfc.algafood.api.controller;

import br.com.vfc.algafood.api.assembler.PermissionAssembler;
import br.com.vfc.algafood.domain.model.Group;
import br.com.vfc.algafood.domain.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("groups/{groupId}/permissions")
public class GroupPermissionController {

    private GroupService groupService;

    private PermissionAssembler permissionAssembler;

    @Autowired
    public GroupPermissionController(GroupService groupService, PermissionAssembler permissionAssembler) {
        this.groupService = groupService;
        this.permissionAssembler = permissionAssembler;
    }

    @GetMapping
    public ResponseEntity<?> list(@PathVariable Long groupId) {

        Group group = groupService.findById(groupId);

        return ResponseEntity.ok(permissionAssembler.toCollectionModel(group.getPermissions()));
    }

    @PutMapping("/{permissionId}")
    public void assign(@PathVariable Long groupId, @PathVariable Long permissionId) {

        groupService.assignPermission(groupId, permissionId);
    }

    @DeleteMapping("/{permissionId}")
    public void remove(@PathVariable Long groupId, @PathVariable Long permissionId) {

        groupService.removePermission(groupId, permissionId);
    }
}
