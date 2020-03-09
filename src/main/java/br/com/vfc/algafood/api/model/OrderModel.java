package br.com.vfc.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class OrderModel {

    private Long id;

    private String code;

    private BigDecimal subTotal;

    private BigDecimal shippingFee;

    private BigDecimal totalValue;

    private OffsetDateTime createdDate;

    private OffsetDateTime confirmationDate;

    private OffsetDateTime deliveryDate;

    private OffsetDateTime cancellationDate;

    private String status;

    private RestaurantResumeModel restaurant;

    private UserModel customer;

    private AddressModel deliveryAddress;

    private PaymentMethodModel paymentMethod;

    private Set<OrderItemModel> items = new HashSet<>();
}
