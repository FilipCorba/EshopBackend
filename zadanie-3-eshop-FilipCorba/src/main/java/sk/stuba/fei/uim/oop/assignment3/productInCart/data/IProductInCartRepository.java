package sk.stuba.fei.uim.oop.assignment3.productInCart.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface IProductInCartRepository extends JpaRepository<ProductInCart, Long> {
    List<ProductInCart> findAll();

    ProductInCart findProductInCartByProductId( Long productId);
}
