package br.com.vfc.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemInput {

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @NotNull
    @PositiveOrZero
    private BigDecimal unitValue;

    @NotNull
    @PositiveOrZero
    private BigDecimal totalValue;

    private String observation;

    @NotNull
    @Valid
    private ProductIdInput product;
}
