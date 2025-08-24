package com.local.ecom.execution.controllers.transaction;

import com.fasterxml.jackson.databind.JsonNode;
import com.local.ecom.data.entities.transaction.Transaction;
import com.local.ecom.data.services.transaction.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public List<Transaction> getTransactions(@RequestParam(required = false) String filter) {
        return transactionService.getAll(filter);
    }

    @PostMapping
    public ResponseEntity<Object> addTransactions(@RequestBody JsonNode jsonNode) {
        return transactionService.addAll(jsonNode,Transaction.class);
    }

    @PutMapping
    public ResponseEntity<Object> updateTransactions(@RequestBody JsonNode jsonNode) {
        return transactionService.updateAll(jsonNode, Transaction.class);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteTransactions(@RequestBody JsonNode jsonNode) {
        return transactionService.deleteAll(jsonNode, Transaction.class);
    }
}