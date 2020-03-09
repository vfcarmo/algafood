package br.com.vfc.algafood.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tab_payment_method")
public class PaymentMethod extends BaseEntity {

    @Column(nullable = false)
    private String name;
}
