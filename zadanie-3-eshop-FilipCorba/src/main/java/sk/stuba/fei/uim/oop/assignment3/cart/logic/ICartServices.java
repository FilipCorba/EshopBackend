package sk.stuba.fei.uim.oop.assignment3.cart.logic;

import sk.stuba.fei.uim.oop.assignment3.cart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.exeption.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exeption.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.productInCart.web.ProductInCartRequest;

public interface ICartServices {
    Cart create();



    Cart getById(long id) throws NotFoundException;

    void deleteCart(long id) throws NotFoundException, IllegalOperationException;

    Cart addToCart(long id, ProductInCartRequest body) throws NotFoundException, IllegalOperationException;

    Double payForCart(Long id) throws NotFoundException, IllegalOperationException;
}
