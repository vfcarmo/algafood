package br.com.vfc.algafood.api.assembler;

import br.com.vfc.algafood.api.model.PaymentMethodModel;
import br.com.vfc.algafood.api.model.input.PaymentMethodInput;
import br.com.vfc.algafood.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodAssembler extends AbstractAssembler<PaymentMethodInput, PaymentMethod, PaymentMethodModel> {
    /**
     * Construtor.
     *
     * @param modelMapper Mapper responsible for conversions.
     */
    @Autowired
    public PaymentMethodAssembler(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
