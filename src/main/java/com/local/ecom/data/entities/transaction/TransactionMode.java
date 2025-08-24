package com.local.ecom.data.entities.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "transaction_modes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionMode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @NotBlank
    @Column(name = "transaction_mode", unique = true, nullable = false, length = 20)
    @JsonProperty("transaction_mode")
    private String transactionModeName;

    @NotNull
    @Column(nullable = false)
    @JsonProperty("availability")
    private Boolean availability;
}