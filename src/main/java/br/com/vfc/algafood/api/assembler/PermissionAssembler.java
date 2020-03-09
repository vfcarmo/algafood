package br.com.vfc.algafood.api.assembler;

import br.com.vfc.algafood.api.model.PermissionModel;
import br.com.vfc.algafood.api.model.input.PermissionInput;
import br.com.vfc.algafood.domain.model.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionAssembler extends AbstractAssembler<PermissionInput, Permission, PermissionModel> {
    /**
     * Construtor.
     *
     * @param modelMapper Mapper responsible for conversions.
     */
    @Autowired
    public PermissionAssembler(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
