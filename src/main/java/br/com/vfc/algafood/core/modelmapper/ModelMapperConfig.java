package br.com.vfc.algafood.core.modelmapper;

import br.com.vfc.algafood.api.model.AddressModel;
import br.com.vfc.algafood.domain.model.Address;
import br.com.vfc.algafood.domain.model.OrderStatus;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Converter<String, OrderStatus> orderStatusConverter = new AbstractConverter<>() {
            @Override
            protected OrderStatus convert(String source) {
                OrderStatus orderStatus;
                try {
                    orderStatus = OrderStatus.valueOf(source.toUpperCase());
                } catch (Exception e) {
                    orderStatus = null;
                }
                return orderStatus;
            }
        };
        modelMapper.addConverter(orderStatusConverter);
        var addressToAddressModelTypeMap = modelMapper.createTypeMap(Address.class, AddressModel.class);
        addressToAddressModelTypeMap.<String>addMapping(
                src -> src.getCity().getState().getName(),
                (dest, value) -> dest.getCity().setState(value));
        return modelMapper;
    }
}
