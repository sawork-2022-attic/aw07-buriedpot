package com.micropos.carts.service;

import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Item;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart newCart();

    Double checkout(Cart cart);

    Double checkout(Integer cartId);

    Cart add(Cart cart, Item item);

    List<Cart> getAllCarts();

    Cart addCart(Cart cart);

    Optional<Cart> getCart(Integer cartId);

    Integer test();

    Cart delete(Cart cart, String productId); // delete an item in the cart by productId

    Cart empty(Cart cart);//
}
