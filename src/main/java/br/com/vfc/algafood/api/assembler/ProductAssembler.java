package br.com.vfc.algafood.api.assembler;

import br.com.vfc.algafood.api.model.ProductModel;
import br.com.vfc.algafood.api.model.input.ProductInput;
import br.com.vfc.algafood.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductAssembler extends AbstractAssembler<ProductInput, Product, ProductModel> {
    /**
     * Construtor.
     *
     * @param modelMapper Mapper responsible for conversions.
     */
    @Autowired
    public ProductAssembler(ModelMapper modelMapper) {
        super(modelMapper);
    }
}
