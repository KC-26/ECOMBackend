package com.local.ecom.data.entities.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.local.ecom.data.entities.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@ToString(exclude = "role")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @JsonProperty("id")
    protected Long id;

    @NotBlank
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @JsonProperty("name")
    protected String name;

    @Email
    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    @JsonProperty("email")
    protected String email;

    @NotBlank
    @Column(nullable = false, length = 10, unique = true)
    @EqualsAndHashCode.Include
    @JsonProperty("username")
    protected String username;

    @NotBlank
    @Column(nullable = false)
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    protected String password;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @JsonProperty("dob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    protected LocalDate dob;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "role_id",
            foreignKey = @ForeignKey(name = "fk_user_role")
    )
    @JsonProperty("role_id")
    protected Role role;

    public Long getRole() {
        if(role == null) {
            return null;
        } else {
            return role.getId();
        }
    }
}