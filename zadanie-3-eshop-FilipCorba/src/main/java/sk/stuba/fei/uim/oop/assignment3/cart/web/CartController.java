package sk.stuba.fei.uim.oop.assignment3.cart.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.cart.logic.ICartServices;
import sk.stuba.fei.uim.oop.assignment3.cart.web.bodies.CartResponse;
import sk.stuba.fei.uim.oop.assignment3.exeption.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exeption.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.productInCart.web.ProductInCartRequest;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartServices service;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResponse> addCart() {
        return new ResponseEntity<>(new CartResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse getCart(@PathVariable("id") long cartId) throws NotFoundException {
        return new CartResponse(this.service.getById(cartId));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCart(@PathVariable("id") Long cartId) throws NotFoundException, IllegalOperationException {
        this.service.deleteCart(cartId);
    }


    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse addToCart(@PathVariable("id") long cartId, @RequestBody ProductInCartRequest body) throws NotFoundException, IllegalOperationException {
        return new CartResponse(this.service.addToCart(cartId, body));
    }

    @GetMapping(value = "/{id}/pay")
    public ResponseEntity<String> payForCart(@PathVariable("id") Long cartId) throws NotFoundException, IllegalOperationException {
        return ResponseEntity.ok(Double.toString(this.service.payForCart(cartId)));
    }
}
