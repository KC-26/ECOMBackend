package com.local.ecom.execution.controllers.transaction;

import com.fasterxml.jackson.databind.JsonNode;
import com.local.ecom.data.entities.transaction.TransactionMode;
import com.local.ecom.data.services.transaction.TransactionModeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RestController
@RequestMapping("/transaction_mode")
public class TransactionModeController {

    private final TransactionModeService transactionModeService;

    @GetMapping
    public List<TransactionMode> getTransactionModes(@RequestParam(required = false) String filter) {
        return transactionModeService.getAll(filter);
    }

    @PostMapping
    public ResponseEntity<Object> addTransactionModes(@RequestBody JsonNode jsonNode) {
        return transactionModeService.addAll(jsonNode, TransactionMode.class);
    }

    @PutMapping
    public ResponseEntity<Object> updateTransactionModes(@RequestBody JsonNode jsonNode) {
        return transactionModeService.updateAll(jsonNode, TransactionMode.class);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteTransactionModes(@RequestBody JsonNode jsonNode) {
        return transactionModeService.deleteAll(jsonNode, TransactionMode.class);
    }
}
