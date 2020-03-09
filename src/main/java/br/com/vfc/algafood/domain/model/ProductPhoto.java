package br.com.vfc.algafood.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tab_product_photo")
public class ProductPhoto extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long length;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product"))
    private Product product;
}
