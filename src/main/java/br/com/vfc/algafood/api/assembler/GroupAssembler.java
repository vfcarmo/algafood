package br.com.vfc.algafood.api.assembler;

import br.com.vfc.algafood.api.model.GroupModel;
import br.com.vfc.algafood.api.model.input.GroupInput;
import br.com.vfc.algafood.domain.model.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupAssembler extends AbstractAssembler<GroupInput, Group, GroupModel> {
    /**
     * Construtor.
     *
     * @param modelMapper Mapper responsible for conversions.
     */
    @Autowired
    public GroupAssembler(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
