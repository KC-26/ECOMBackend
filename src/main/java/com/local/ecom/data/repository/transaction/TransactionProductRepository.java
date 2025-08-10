package com.local.ecom.data.repository.transaction;

import com.local.ecom.data.entities.transaction.TransactionProductId;
import com.local.ecom.data.entities.transaction.TransactionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionProductRepository extends JpaRepository<TransactionProduct, TransactionProductId>, JpaSpecificationExecutor<TransactionProduct> {}