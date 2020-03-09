package br.com.vfc.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tab_state")
public class State extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
    @JsonIgnore
    private Set<City> cities = new HashSet<>();
}
