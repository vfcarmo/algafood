package br.com.vfc.algafood.domain.model;

import br.com.vfc.algafood.core.validation.ShippingFee;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tab_restaurant")
public class Restaurant extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ShippingFee
    @Column(name = "shipping_fee", nullable = false)
    private BigDecimal shippingFee;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private Boolean open = true;

    @ManyToOne
    @JoinColumn(name = "cookery_id", nullable = false, foreignKey = @ForeignKey(name = "fk_cookery"))
    private Cookery cookery;

    @Embedded
    private Address address;

    @ManyToMany
    @JoinTable(name = "tab_restaurant_payment_method",
            joinColumns = @JoinColumn(name = "restaurant_id", foreignKey = @ForeignKey(name = "fk_restaurant_payment_method")),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id", foreignKey = @ForeignKey(name = "fk_payment_method_restaurant"))
    )
    private Set<PaymentMethod> paymentMethods = new HashSet<>();

    @OneToMany(mappedBy = "restaurant")
    private Set<Product> products = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tab_restaurant_user",
            joinColumns = @JoinColumn(name = "restaurant_id", foreignKey = @ForeignKey(name = "fk_restaurant_responsible")),
            inverseJoinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_responsible_restaurant"))
    )
    private Set<User> responsible = new HashSet<>();

    public void open() {
        setOpen(true);
    }

    public void close() {
        setOpen(false);
    }

    public void active() {
        setActive(true);
    }

    public void inactive() {
        setActive(false);
    }

    public boolean addPaymentMethod(PaymentMethod paymentMethod) {
        return getPaymentMethods().add(paymentMethod);
    }

    public boolean removePaymentMethod(PaymentMethod paymentMethod) {
        return getPaymentMethods().remove(paymentMethod);
    }

    public boolean addResponsible(User user) {
        return getResponsible().add(user);
    }

    public boolean removeResponsible(User user) {
        return getResponsible().remove(user);
    }

}
