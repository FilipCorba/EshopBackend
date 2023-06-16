package sk.stuba.fei.uim.oop.assignment3.productInCart.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInCartRequest {

    private int amount;
    private Long productId;

}
