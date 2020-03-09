package br.com.vfc.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GroupModel {

    private Long id;

    private String name;

    private Set<PermissionModel> permissions;
}
