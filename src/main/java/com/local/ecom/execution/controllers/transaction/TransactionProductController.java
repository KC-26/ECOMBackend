package com.local.ecom.execution.controllers.transaction;

import com.fasterxml.jackson.databind.JsonNode;
import com.local.ecom.data.entities.transaction.TransactionProduct;
import com.local.ecom.data.services.transaction.TransactionProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RestController
@RequestMapping("/transaction_product")
public class TransactionProductController {

    private final TransactionProductService transactionProductService;

    @GetMapping
    public List<TransactionProduct> getTransactionProducts(@RequestParam(required = false) String filter) {
        return transactionProductService.getAll(filter);
    }

    @PostMapping
    public ResponseEntity<Object> addTransactionProducts(@RequestBody JsonNode jsonNode) {
        return transactionProductService.addAll(jsonNode, TransactionProduct.class);
    }

    @PutMapping
    public ResponseEntity<Object> updateTransactionProducts(@RequestBody JsonNode jsonNode) {
        return transactionProductService.updateAll(jsonNode, TransactionProduct.class);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteTransactionProducts(@RequestBody JsonNode jsonNode) {
        return transactionProductService.deleteAll(jsonNode, TransactionProduct.class);
    }
}