package br.com.vfc.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemModel {

    private Long productId;

    private String productName;

    private Integer quantity;

    private BigDecimal unitValue;

    private BigDecimal totalValue;

    private String observation;

}
