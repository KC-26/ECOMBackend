package com.local.ecom.data.services.cart;

import com.local.ecom.data.entities.cart.Cart;
import com.local.ecom.data.repository.cart.CartRepository;
import com.local.ecom.data.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService extends BaseService<Cart, Long> {

    @Autowired
    public CartService(CartRepository repository) {
        super(repository, repository);
    }
}