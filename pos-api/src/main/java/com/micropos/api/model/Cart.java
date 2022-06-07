package com.micropos.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Data
@Component
@SessionScope
public class Cart implements Serializable {

    // private Integer id = UUID.randomUUID();

    private List<Item> items = new ArrayList<>();

    /*public boolean addItem(Item item) {
        return items.add(item);
    }
*/
    public double getTotal() {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }
        return total;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean addItem(Item item) {

        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item item1 = it.next();
            if (item1.getProduct().getId().equals(item.getProduct().getId())) {
                int newAmount = item1.getQuantity() + item.getQuantity();
                if (newAmount <= 0) {
                    it.remove();
                }
                else {
                    item1.setQuantity(newAmount);
                }

                System.out.println("has el: " + this);
                return true;
            }
        }
        if (item.getQuantity() <= 0) return false;
        System.out.println("new has el: " + this);
        return items.add(item);
    }

    public boolean deleteItem(String productId) {
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item item = it.next();
            if (item.getProduct().getId().equals(productId)) {
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
            if (item.getProduct().getId().equals(productId)) {
                if (amount > 0) {
                    item.setQuantity(amount);
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
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }
}
