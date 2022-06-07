package com.micropos.api.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropos.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ApiServiceImpl implements ApiService {
    /*private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }


    @Override
    public Product randomProduct() {
        return products().get(ThreadLocalRandom.current().nextInt(0, products().size()));
    }

    @Override
    public void checkout(Cart cart) {

    }

    @Override
    public Cart add(Cart cart, Product product, int amount) {
        return add(cart, product.getId(), amount);
    }

    @Override
    public Cart add(Cart cart, String productId, int amount) {

        Product product = getProduct(productId);
        if (product == null) return cart;

        cart.addItem(new Item(product, amount));
        return cart;
    }

    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }

    @Override
    public void total(Cart cart) {

    }


    @Override
    public boolean delete(Cart cart, String productId) {
        return cart.deleteItem(productId);
    }

    @Override
    public boolean modify(Cart cart, String productId, int amount) {
        return cart.modifyItem(productId, amount);
    }

    @Override
    public boolean empty(Cart cart) {
        return cart.empty();
    }

    @Override
    public Product getProduct(String productId) {
        for (Product p : products()) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }*/

}
