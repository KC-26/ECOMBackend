package com.local.ecom.data.repository.transaction;

import com.local.ecom.data.entities.transaction.TransactionMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionModeRepository extends JpaRepository<TransactionMode, Long>, JpaSpecificationExecutor<TransactionMode> {}