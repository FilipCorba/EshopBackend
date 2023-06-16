package sk.stuba.fei.uim.oop.assignment3.cart.web.bodies;


import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cart.data.Cart;

import sk.stuba.fei.uim.oop.assignment3.productInCart.web.ProductInCartResponse;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CartResponse {
    private long id;
    private List<ProductInCartResponse> shoppingList;

    private boolean payed;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.shoppingList = cart.getListInCart().stream().map(ProductInCartResponse::new).collect(Collectors.toList());
        this.payed = cart.isPayed();

    }
}
