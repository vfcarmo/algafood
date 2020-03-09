package br.com.vfc.algafood.core.jackson;

import br.com.vfc.algafood.api.model.mixin.CityMixin;
import br.com.vfc.algafood.api.model.mixin.ProductMixin;
import br.com.vfc.algafood.api.model.mixin.RestaurantMixin;
import br.com.vfc.algafood.domain.model.City;
import br.com.vfc.algafood.domain.model.Product;
import br.com.vfc.algafood.domain.model.Restaurant;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {

        setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
        setMixInAnnotation(City.class, CityMixin.class);
        setMixInAnnotation(Product.class, ProductMixin.class);
    }
}
