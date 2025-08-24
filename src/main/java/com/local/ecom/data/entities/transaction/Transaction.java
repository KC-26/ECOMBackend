package com.local.ecom.data.entities.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.local.ecom.data.entities.user.CustomerUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @NotBlank
    @Column(name = "transaction_id", unique = true, nullable = false, length = 15)
    @JsonProperty("transaction_id")
    private String transactionId;

    @NotNull
    @Column(name = "transaction_cost", nullable = false)
    @JsonProperty("transaction_cost")
    private Long transactionCost;

    @ManyToOne
    @JoinColumn(
            name = "transaction_user",
            foreignKey = @ForeignKey(name = "fk_transaction_user")
    )
    @JsonProperty("transaction_user")
    private CustomerUser user;

    @ManyToOne
    @JoinColumn(
            name = "transaction_mode",
            foreignKey = @ForeignKey(name = "fk_transaction_mode")
    )
    @JsonProperty("transaction_mode")
    private TransactionMode transactionMode;
}