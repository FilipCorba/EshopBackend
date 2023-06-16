package sk.stuba.fei.uim.oop.assignment3.cart.data;


import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.productInCart.data.ProductInCart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToMany
    private List<ProductInCart> listInCart;

    private boolean payed;
    private double price;

    public Cart() {
        this.price= getPrice();
        this.listInCart = new ArrayList<>();
    }


}
