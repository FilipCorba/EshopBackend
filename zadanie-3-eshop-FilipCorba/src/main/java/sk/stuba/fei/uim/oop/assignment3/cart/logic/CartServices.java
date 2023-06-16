package sk.stuba.fei.uim.oop.assignment3.cart.logic;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.cart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.cart.data.ICartRepository;
import sk.stuba.fei.uim.oop.assignment3.exeption.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exeption.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.logic.IProductService;
import sk.stuba.fei.uim.oop.assignment3.productInCart.data.IProductInCartRepository;
import sk.stuba.fei.uim.oop.assignment3.productInCart.data.ProductInCart;
import sk.stuba.fei.uim.oop.assignment3.productInCart.web.ProductInCartRequest;


@Service
@Setter
@Getter
public class CartServices implements ICartServices {
    private double total;
    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductInCartRepository productInCartRepository;


    @Autowired
    private IProductService productService;

    @Override
    public Cart create() {
        return this.cartRepository.save(new Cart());
    }

    @Override
    public Cart getById(long id) throws NotFoundException {
        Cart cart = this.cartRepository.findCartById(id);
        if (cart == null) {
            throw new NotFoundException();
        }
        return cart;
    }

    @Override
    public void deleteCart(long id) throws NotFoundException {
        Cart cart = this.getById(id);
        this.cartRepository.delete(cart);
    }


    @Override
    public Cart addToCart(long id, ProductInCartRequest body) throws NotFoundException, IllegalOperationException {

        Cart cart = this.getCartId(id);

        Product product = this.productService.getById(body.getProductId());
        if (product.getAmount() < body.getAmount()) {
            throw new IllegalOperationException();
        }
        ProductInCart productInCart = this.productInCartRepository.findProductInCartByProductId(product.getId());
        if (cart.getListInCart().contains(productInCart)) {
            productInCart.setAmount(productInCart.getAmount() + body.getAmount());
        } else {
            productInCart = new ProductInCart(body);
            cart.getListInCart().add(productInCart);

        }
        this.productInCartRepository.save(productInCart);
        this.productService.decreaseAmountOfProduct(product, body.getAmount());
        return this.cartRepository.save(cart);


    }

    @Override
    public Double payForCart(Long id) throws NotFoundException, IllegalOperationException {
        Cart cart = this.getCartId(id);
        cart.setPayed(true);
        this.cartRepository.save(cart);
        for (ProductInCart productInCart : cart.getListInCart()) {
            Product product = productService.getById(productInCart.getProductId());
            cart.setPrice(cart.getPrice() + (product.getPrice() * productInCart.getAmount()));
        }
        setTotal(cart.getPrice());
        return getTotal();
    }

    private Cart getCartId(long id) throws NotFoundException, IllegalOperationException {
        Cart cart = this.getById(id);
        if (cart.isPayed()) {

            throw new IllegalOperationException();

        }
        return cart;
    }

}
