package com.micropos.carts.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Entity
@Table(name = "Carts")
@Accessors(fluent = true, chain = true)
public class Cart implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Items", joinColumns = @JoinColumn(name = "cart_id"))
    private List<Item> items = new ArrayList<>();

    /*public boolean addItem(Item item) {
        return items.add(item);
    }
*/
    public double getTotal() {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).quantity() * items.get(i).productPrice();
        }
        this.
        return total;
    }

    /*public List<Item> getItems() {
        return items;
    }*/

    public boolean addItem(Item item) {

        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item item1 = it.next();
            if (item1.productId().equals(item.productId())) {
                int newAmount = item1.quantity() + item.quantity();
                if (newAmount <= 0) {
                    it.remove();
                }
                else {
                    item1.quantity(newAmount);
                }

                System.out.println("has el: " + this);
                return true;
            }
        }
        if (item.quantity() <= 0) return false;
        System.out.println("new has el: " + this);
        return items.add(item);
    }

    public boolean deleteItem(String productId) {
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item item = it.next();
            if (item.productId().equals(productId)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public boolean modifyItem(String productId, int amount) {
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item item = it.next();
            if (item.productId().equals(productId)) {
                if (amount > 0) {
                    item.quantity(amount);
                }
                else {
                    it.remove();
                }
                return true;
            }
        }
        return false;
    }

    public boolean empty() {
        items = new ArrayList<>();
        return true;
    }


    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).quantity() * items.get(i).productPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }
}
