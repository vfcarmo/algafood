package br.com.vfc.algafood.api.assembler;

import br.com.vfc.algafood.api.model.CookeryModel;
import br.com.vfc.algafood.api.model.input.CookeryInput;
import br.com.vfc.algafood.domain.model.Cookery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CookeryAssembler extends AbstractAssembler<CookeryInput, Cookery, CookeryModel> {

    /**
     * Construtor.
     *
     * @param modelMapper Mapper responsible for conversions.
     */
    @Autowired
    public CookeryAssembler(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
