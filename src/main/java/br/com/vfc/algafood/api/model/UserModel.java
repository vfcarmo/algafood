package br.com.vfc.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserModel {

    private Long id;

    private String name;

    private String email;

    private List<GroupModel> group;
}
