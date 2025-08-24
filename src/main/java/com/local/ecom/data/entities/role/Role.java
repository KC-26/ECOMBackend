package com.local.ecom.data.entities.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.local.ecom.data.entities.user.CustomerUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {

    public Role(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @JsonProperty("id")
    private Long id;

    @NotBlank
    @Column(name = "role_name", nullable = false, length = 30, unique = true)
    @JsonProperty("role_name")
    @EqualsAndHashCode.Include
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("users")
    private List<CustomerUser> users = new ArrayList<>();

    public Long[] getUsers() {
        if(users == null) {
            return new Long[0];
        } else {
            return users.stream().map(CustomerUser::getId).toArray(Long[]::new);
        }
    }
}