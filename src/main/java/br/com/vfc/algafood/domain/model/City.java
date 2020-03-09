package br.com.vfc.algafood.domain.model;

import br.com.vfc.algafood.core.validation.Groups;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

@Data
@Entity
@Table(name = "tab_city")
public class City extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Valid
    @ConvertGroup(to = Groups.StateId.class)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false, foreignKey = @ForeignKey(name = "fk_state"))
    private State state;
}
