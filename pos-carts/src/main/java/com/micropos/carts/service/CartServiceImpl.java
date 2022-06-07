package com.micropos.carts.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropos.carts.mapper.CartMapper;
import com.micropos.carts.model.Item;
import com.micropos.carts.repository.CartRepository;
import com.micropos.carts.model.Cart;
import com.micropos.carts.repository.ItemRepository;
import com.micropos.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private ItemRepository itemRepository;


    private final String COUNTER_URL = "http://POS-COUNTER/counter/";

    private CartMapper cartMapper;

    @Autowired
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    @LoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Double checkout(Cart cart) {
        CartDto cartDto = cartMapper.toCartDto(cart);
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = null;
        try {
            request = new HttpEntity<>(mapper.writeValueAsString(cartDto), headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Double total = restTemplate.postForObject(COUNTER_URL+ "/checkout", request, Double.class);
        return total;
    }

    public Integer test() {

        Integer test = restTemplate.getForObject(COUNTER_URL + "/test", Integer.class);
        return test;
    }

    @Override
    public Double checkout(Integer cartId) {
        Optional<Cart> cart = this.cartRepository.findById(cartId);

        if (cart.isEmpty()) return Double.valueOf(-1);

        return this.checkout(cart.get());
    }

    @Override
    public Cart add(Cart cart, Item item) {
        itemRepository.save(item);
        if (cart.addItem(item))
            return cartRepository.save(cart);
        return null;
    }

    @Override
    public Cart delete(Cart cart, String productId) {
        cart.deleteItem(productId);
        return cart;
    }

    @Override
    public Cart empty(Cart cart) {
        cart.empty();
        return cart;
    }

    @Override
    public List<Cart> getAllCarts() {
        return Streamable.of(cartRepository.findAll()).toList();
    }

    @Override
    public Optional<Cart> getCart(Integer cartId) {
        return cartRepository.findById(cartId);
    }

    @Override
    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart newCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
        return cart;
    }
}
