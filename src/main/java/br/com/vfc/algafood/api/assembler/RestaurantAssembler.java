package br.com.vfc.algafood.api.assembler;

import br.com.vfc.algafood.api.model.RestaurantModel;
import br.com.vfc.algafood.api.model.input.RestaurantInput;
import br.com.vfc.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantAssembler extends AbstractAssembler<RestaurantInput, Restaurant, RestaurantModel> {
    /**
     * Construtor.
     *
     * @param modelMapper Mapper responsible for conversions.
     */
    @Autowired
    public RestaurantAssembler(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
