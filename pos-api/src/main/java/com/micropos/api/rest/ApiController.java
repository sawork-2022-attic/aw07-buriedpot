package com.micropos.api.rest;


import com.micropos.api.dto.CartDto;
import com.micropos.api.dto.ProductDto;
import com.micropos.api.feign.CartFeignClient;
import com.micropos.api.feign.ProductFeignClient;
import com.micropos.api.service.ApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class ApiController {


    @Autowired
    private ApiService cartService;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private CartFeignClient cartFeignClient;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> showProducts() {
        ResponseEntity<List<ProductDto>> productDtos = productFeignClient.listProducts();
        return productDtos;
    }

    @GetMapping("/carts")
    public ResponseEntity<List<CartDto>> showCarts() {
        ResponseEntity<List<CartDto>> cartDtos = cartFeignClient.listCarts();
        return cartDtos;
    }

    @GetMapping("/carts/cart/{cartId}")
    public ResponseEntity<CartDto> showCartById(@PathVariable("cartId") Integer cartId) {
        ResponseEntity<CartDto> cartDto = cartFeignClient.showCartById(cartId);
        return cartDto;
    }
}
