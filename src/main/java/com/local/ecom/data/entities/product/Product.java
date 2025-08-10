package com.local.ecom.data.entities.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @NotBlank
    @Column(name = "product_id", unique = true, nullable = false, length = 15)
    @JsonProperty("product_id")
    private String productId;

    @NotBlank
    @Column(name = "product_description", nullable = false, length = 200)
    @JsonProperty("product_description")
    private String productDescription;

    @NotBlank
    @Column(name = "product_revision", nullable = false, length = 5)
    @JsonProperty("product_revision")
    private String productRevision;

    @NotNull
    @Positive
    @Column(name = "product_cost", nullable = false)
    @JsonProperty("product_cost")
    private Double productCost;

    @NotNull
    @Positive
    @Column(name = "product_selling_price", nullable = false)
    @JsonProperty("product_selling_price")
    private Double productSellingPrice;

    @NotNull
    @Min(0)
    @Column(name = "discount", nullable = false)
    @JsonProperty("discount")
    private Integer discount;

    @NotNull
    @Min(0)
    @Column(name = "available_stock", nullable = false)
    @JsonProperty("available_stock")
    private Integer availableStock;
}