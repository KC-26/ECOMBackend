package com.local.ecom.data.services.transaction;

import com.local.ecom.data.entities.transaction.TransactionMode;
import com.local.ecom.data.repository.transaction.TransactionModeRepository;
import com.local.ecom.data.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionModeService extends BaseService<TransactionMode, Long> {

    @Autowired
    public TransactionModeService(TransactionModeRepository repository) {
        super(repository, repository);
    }
}