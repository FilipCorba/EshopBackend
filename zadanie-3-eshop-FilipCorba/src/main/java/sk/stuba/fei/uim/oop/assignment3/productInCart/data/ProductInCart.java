package sk.stuba.fei.uim.oop.assignment3.productInCart.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import sk.stuba.fei.uim.oop.assignment3.productInCart.web.ProductInCartRequest;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Getter
@Setter
@Entity
@NoArgsConstructor
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;

    private int amount;



    public ProductInCart(ProductInCartRequest r) {

        this.productId = r.getProductId();
        this.amount = r.getAmount();


    }
}
