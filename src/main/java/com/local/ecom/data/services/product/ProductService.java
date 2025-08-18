package com.local.ecom.data.services.product;

import com.local.ecom.data.entities.product.Product;
import com.local.ecom.data.repository.product.ProductRepository;
import com.local.ecom.data.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product, Long> {

    @Autowired
    public ProductService(ProductRepository repository) {
        super(repository, repository);
    }
}