package br.com.vfc.algafood.api.assembler;

import br.com.vfc.algafood.api.model.OrderModel;
import br.com.vfc.algafood.api.model.input.OrderInput;
import br.com.vfc.algafood.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAssembler extends AbstractAssembler<OrderInput, Order, OrderModel> {
    /**
     * Construtor.
     *
     * @param modelMapper Mapper responsible for conversions.
     */
    @Autowired
    public OrderAssembler(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
