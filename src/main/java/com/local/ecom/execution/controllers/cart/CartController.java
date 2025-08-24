package com.local.ecom.execution.controllers.cart;

import com.fasterxml.jackson.databind.JsonNode;
import com.local.ecom.data.entities.cart.Cart;
import com.local.ecom.data.services.cart.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<Cart> getCarts(@RequestParam(required = false) String filter) {
        return cartService.getAll(filter);
    }

    @PostMapping
    public ResponseEntity<Object> addCarts(@RequestBody JsonNode jsonNode) {
        return cartService.addAll(jsonNode, Cart.class);
    }

    @PutMapping
    public ResponseEntity<Object> updateCarts(@RequestBody JsonNode jsonNode) {
        return cartService.updateAll(jsonNode, Cart.class);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCarts(@RequestBody JsonNode jsonNode) {
        return cartService.deleteAll(jsonNode, Cart.class);
    }
}