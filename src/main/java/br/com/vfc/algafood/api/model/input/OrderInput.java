package br.com.vfc.algafood.api.model.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class OrderInput {

    @NotBlank
    private String code;

    @NotNull
    @PositiveOrZero
    private BigDecimal subTotal;

    @NotNull
    @PositiveOrZero
    private BigDecimal shippingFee;

    @NotNull
    @PositiveOrZero
    private BigDecimal totalValue;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime confirmationDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private OffsetDateTime deliveryDate = OffsetDateTime.now();

    @NotNull
    @Valid
    private RestaurantIdInput restaurant;

    @NotNull
    @Valid
    private UserIdInput customer;

    @NotNull
    @Valid
    private AddressInput deliveryAddress;

    @NotNull
    @Valid
    private PaymentMethodIdInput paymentMethod;

    @NotNull
    @NotEmpty
    @Valid
    private Set<OrderItemInput> items = new HashSet<>();
}
