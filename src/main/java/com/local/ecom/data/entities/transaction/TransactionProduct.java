package com.local.ecom.data.entities.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.local.ecom.data.entities.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionProduct {

    @EmbeddedId
    @JsonProperty("transaction_product_id")
    private TransactionProductId transactionProductId;

    @ManyToOne
    @MapsId("transactionId")
    @JoinColumn(
            name = "transaction_id",
            foreignKey = @ForeignKey(name = "fk_trasaction_id")
    )
    @JsonProperty("transaction")
    private Transaction transaction;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name = "fk_product_id")
    )
    @JsonProperty("product")
    private Product product;

    @Column(nullable = false)
    @JsonProperty("quantity")
    private Integer quantity = 1;

    @Column(name = "product_cost", nullable = false, precision = 10, scale = 2)
    @JsonProperty("product_cost")
    private BigDecimal productCost;
}