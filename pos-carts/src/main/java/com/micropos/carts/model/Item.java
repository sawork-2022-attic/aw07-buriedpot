package com.micropos.carts.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="Items")
@Accessors(fluent = true, chain = true)
public class Item implements Serializable {
    /**
     * product's feature, no altenative so put here
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column(name="cart_id")
    @Getter
    @Setter
    private Integer cartId;


    @Getter
    @Setter
    @Column(name="product_id")
    private String productId;

    @Getter
    @Setter
    @Column(name="product_name")
    private String productName;

    @Getter
    @Setter
    @Column(name="product_price")
    private double productPrice;

/*
    @Getter
    @Setter
    @Column(name="item_image")
    private String image;
*/

    @Getter
    @Setter
    @Column(name="quantity")
    private int quantity;

}
