package com.local.ecom.data.entities.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.local.ecom.data.entities.product.Product;
import com.local.ecom.data.entities.user.CustomerUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "carts")
@Getter
@Setter
@ToString(exclude = {"customerUser","product"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @JsonProperty("id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_cart_user"))
    @JsonProperty("user_id")
    private CustomerUser customerUser;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_cart_product"))
    @JsonProperty("product_id")
    private Product product;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @JsonProperty("quantity")
    private Integer quantity = 1;
}