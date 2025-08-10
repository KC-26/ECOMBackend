package com.local.ecom.data.entities.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class CustomerUser extends BaseUser {

    @NotBlank
    @Column(nullable = false, length = 500)
    @EqualsAndHashCode.Include
    @JsonProperty("address")
    private String address;
}