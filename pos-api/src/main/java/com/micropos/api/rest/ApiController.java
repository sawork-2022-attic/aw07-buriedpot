package com.micropos.api.rest;

import com.micropos.api.CartsApi;
import com.micropos.api.feign.ProductFeignClient;
import com.micropos.api.service.ApiService;
import com.micropos.dto.CartDto;
import com.micropos.dto.ItemDto;
import com.micropos.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class ApiController implements CartsApi {

    @Autowired
    private ApiService cartService;

    @Autowired
    private ProductFeignClient productFeignClient;

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductDto>> showProducts() {
        ResponseEntity<List<ProductDto>> productDtos = productFeignClient.listProducts();
        return productDtos;
    }

}
