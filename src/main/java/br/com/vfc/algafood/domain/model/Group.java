package br.com.vfc.algafood.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tab_group")
public class Group extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "tab_group_permission",
            joinColumns = @JoinColumn(name = "group_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_group_permission")),
            inverseJoinColumns = @JoinColumn(name = "permission_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_permission_group"))
    )
    private Set<Permission> permissions = new HashSet<>();

    public boolean addPermission(Permission permission) {
        return getPermissions().add(permission);
    }

    public boolean removePermission(Permission permission) {
        return getPermissions().remove(permission);
    }
}
