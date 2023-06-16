package sk.stuba.fei.uim.oop.assignment3.productInCart.web;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.productInCart.data.ProductInCart;

@Getter
public class ProductInCartResponse {
    private final int amount;
    private final Long productId;

    public ProductInCartResponse(ProductInCart p){

        this.productId = p.getProductId();
        this.amount = p.getAmount();


    }
}
