package br.com.vfc.algafood.api.assembler;

import br.com.vfc.algafood.api.model.CityModel;
import br.com.vfc.algafood.api.model.input.CityInput;
import br.com.vfc.algafood.domain.model.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityAssembler extends AbstractAssembler<CityInput, City, CityModel> {
    /**
     * Construtor.
     *
     * @param modelMapper Mapper responsible for conversions.
     */
    @Autowired
    public CityAssembler(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
