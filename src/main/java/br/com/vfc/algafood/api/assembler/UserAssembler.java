package br.com.vfc.algafood.api.assembler;

import br.com.vfc.algafood.api.model.UserModel;
import br.com.vfc.algafood.api.model.input.UserInput;
import br.com.vfc.algafood.api.model.input.UserUpdateInput;
import br.com.vfc.algafood.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler extends AbstractAssembler<UserInput, User, UserModel> {
    /**
     * Construtor.
     *
     * @param modelMapper Mapper responsible for conversions.
     */
    @Autowired
    public UserAssembler(ModelMapper modelMapper) {
        super(modelMapper);
    }

    public User toDomain(Long id, UserUpdateInput input) {
        User user = modelMapper.map(input, User.class);
        user.setId(id);
        return user;
    }
}
