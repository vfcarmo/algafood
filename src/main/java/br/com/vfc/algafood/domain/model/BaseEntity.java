package br.com.vfc.algafood.domain.model;

import br.com.vfc.algafood.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class BaseEntity implements Serializable {

    @NotNull(groups = { Groups.CookeryId.class, Groups.StateId.class })
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private OffsetDateTime createdDate;

    @UpdateTimestamp
    private OffsetDateTime updatedDate;

    public boolean isNew() {
        return getId() == null;
    }
}
