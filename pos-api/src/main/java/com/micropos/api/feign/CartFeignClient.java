package com.micropos.api.feign;


import com.micropos.api.dto.CartDto;
import com.micropos.api.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "poscarts")
public interface CartFeignClient {
    @GetMapping("/carts")
    public ResponseEntity<List<CartDto>> listCarts();
    @GetMapping("/carts/cart/{cartId}")
    public ResponseEntity<CartDto> showCartById(@PathVariable(value = "cartId") Integer cartId);
}
