package com.micropos.carts.mapper;

import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Item;
import com.micropos.carts.model.Product;
import com.micropos.dto.CartDto;
import com.micropos.dto.ItemDto;
import com.micropos.dto.ItemDto;
import com.micropos.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper
public interface CartMapper {
    /*Cart toCart(CartDto cartDto);

    CartDto toCartDto(Cart cart);*/

    Collection<CartDto> toCartsDto(Collection<Cart> carts);

    Collection<Cart> toCarts(Collection<CartDto> carts);

    Collection<ProductDto> toProductsDto(Collection<Product> products);

    Collection<Product> toProducts(Collection<ProductDto> products);

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);

    Collection<ItemDto> toItemsDto(Collection<Item> items);

    Collection<Item> toItems(Collection<ItemDto> products);

    Item toItem(ItemDto itemDto);




    default Cart toCart(CartDto cartDto) {
        if (cartDto == null) {
            return null;
        }
        return new Cart().id(cartDto.getId())
                .items(toItems(cartDto.getItems(), cartDto));
    }

    default List<Item> toItems(List<ItemDto> itemsDto, CartDto cartDto) {
        if (itemsDto == null || cartDto == null) {
            return null;
        }
        List<Item> ret = new ArrayList<>();
        for (ItemDto itemDto : itemsDto) {
            ret.add(toItem(itemDto, cartDto));
        }
        return ret;
    }

    default Item toItem(ItemDto itemDto, CartDto cartDto) {
        if (itemDto == null || cartDto == null) {
            return null;
        }
        return new Item().id(itemDto.getId())
                .productPrice(itemDto.getProduct().getPrice())
                .cartId(cartDto.getId())
                .quantity(itemDto.getQuantity())
                .productId(itemDto.getProduct().getId());

    }

    default CartDto toCartDto(Cart cart) {
        if (cart == null) {
            return null;
        }
        return new CartDto().id(cart.id())
                .items(toItemDtos(cart.items()));
    }

    default List<ItemDto> toItemDtos(List<Item> items) {
        if (items == null) {
            return null;
        }
        List<ItemDto> ret = new ArrayList<>();
        for (Item item : items) {
            ret.add(toItemDto(item));
        }

        return ret;
    }

    default Item toItem(ItemDto itemDto, Integer cartId) {
        return new Item().id(itemDto.getId())
                .cartId(cartId)
                .productId(itemDto.getProduct().getId())
                .productName(itemDto.getProduct().getName())
                .quantity(itemDto.getQuantity())
                .productPrice(itemDto.getProduct().getPrice());
    }

    default ItemDto toItemDto(Item item) {
        ProductDto productDto = new ProductDto();
        productDto.setId(item.productId());
        productDto.setName(item.productName());
        productDto.setPrice(item.productPrice());

        return new ItemDto().id(item.id())
                .product(productDto)
                .quantity(item.quantity());
    }

}
