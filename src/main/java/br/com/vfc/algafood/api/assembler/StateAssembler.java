package br.com.vfc.algafood.api.assembler;

import br.com.vfc.algafood.api.model.StateModel;
import br.com.vfc.algafood.api.model.input.StateInput;
import br.com.vfc.algafood.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateAssembler extends AbstractAssembler<StateInput, State, StateModel> {
    /**
     * Construtor.
     *
     * @param modelMapper Mapper responsible for conversions.
     */
    @Autowired
    public StateAssembler(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
