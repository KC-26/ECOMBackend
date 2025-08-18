package com.local.ecom.data.services.transaction;

import com.local.ecom.data.entities.transaction.Transaction;
import com.local.ecom.data.repository.transaction.TransactionRepository;
import com.local.ecom.data.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends BaseService<Transaction, Long> {

    @Autowired
    public TransactionService(TransactionRepository repository) {
        super(repository, repository);
    }
}