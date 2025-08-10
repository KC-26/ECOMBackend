package com.local.ecom.execution.controllers.product;

import com.fasterxml.jackson.databind.JsonNode;
import com.local.ecom.data.entities.product.Product;
import com.local.ecom.data.services.product.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String filter) {
        return productService.getAll(filter);
    }

    @PostMapping
    public ResponseEntity<Object> addProducts(@RequestBody JsonNode jsonNode) {
        return productService.addAll(jsonNode,Product.class);
    }
}