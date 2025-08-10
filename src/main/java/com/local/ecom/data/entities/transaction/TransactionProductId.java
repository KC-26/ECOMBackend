package com.local.ecom.data.entities.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionProductId implements Serializable {

    @Column(name = "id")
    @JsonProperty("transaction_id")
    private Long transactionId;

    @Column(name = "id")
    @JsonProperty("product_id")
    private Long productId;
}