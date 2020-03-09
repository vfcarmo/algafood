package br.com.vfc.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordInput {

    private String password;

    private String newPassword;
}
