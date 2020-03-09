package br.com.vfc.algafood.domain.model;

import br.com.vfc.algafood.core.validation.ValidDateByOrderStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@ValidDateByOrderStatus(statusField = "status",
        deliveryField = "deliveryDate",
        confirmationField = "confirmationDate",
        cancellationField = "cancellationDate")
@Data
@Entity
@Table(name = "tab_order")
public class Order extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private BigDecimal subTotal;

    @Column(nullable = false)
    private BigDecimal shippingFee;

    @Column(nullable = false)
    private BigDecimal totalValue;

    private OffsetDateTime confirmationDate;

    private OffsetDateTime deliveryDate;

    private OffsetDateTime cancellationDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false, foreignKey = @ForeignKey(name = "fk_restaurant"))
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_customer"))
    private User customer;

    @Embedded
    private Address deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false, foreignKey = @ForeignKey(name = "fk_payment_method"))
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items = new HashSet<>();
}
