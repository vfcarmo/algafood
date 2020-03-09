package br.com.vfc.algafood.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tab_user")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "tab_user_group",
            joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_group")),
            inverseJoinColumns = @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_group_user"))
    )
    private Set<Group> groups = new HashSet<>();

    public boolean addGroup(Group group) {
        return getGroups().add(group);
    }

    public boolean removeGroup(Group group) {
        return getGroups().remove(group);
    }
}
