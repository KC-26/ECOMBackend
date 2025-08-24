package com.local.ecom.data.services.transaction;

import com.local.ecom.data.entities.transaction.TransactionProduct;
import com.local.ecom.data.entities.transaction.TransactionProductId;
import com.local.ecom.data.repository.transaction.TransactionProductRepository;
import com.local.ecom.data.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionProductService extends BaseService<TransactionProduct, TransactionProductId> {

    @Autowired
    public TransactionProductService(TransactionProductRepository repository) {
        super(repository, repository);
    }
}