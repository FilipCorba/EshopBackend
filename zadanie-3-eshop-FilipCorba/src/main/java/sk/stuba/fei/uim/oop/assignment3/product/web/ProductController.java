package sk.stuba.fei.uim.oop.assignment3.product.web;

import sk.stuba.fei.uim.oop.assignment3.exeption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.product.logic.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.Amount;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> addproduct(@RequestBody ProductRequest body) throws NotFoundException {
        return new ResponseEntity<>(new ProductResponse(this.service.create(body)), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> getAllproducts() {
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse getProduct(@PathVariable("id") Long ProductId) throws NotFoundException {
        return new ProductResponse(this.service.getById(ProductId));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse updateBook(@PathVariable("id") Long productId, @RequestBody ProductEditRequest body) throws NotFoundException {
        return new ProductResponse(this.service.update(productId, body));
    }
    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable("id") Long productId) throws NotFoundException {
        this.service.delete(productId);
    }
    @GetMapping(value = "/{id}/amount", produces = MediaType.APPLICATION_JSON_VALUE)
    public Amount getAmount(@PathVariable("id") Long bookId) throws NotFoundException {
        return new Amount(this.service.getAmount(bookId));
    }

    @PostMapping(value = "/{id}/amount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Amount addAmount(@PathVariable("id") Long bookId, @RequestBody Amount body) throws NotFoundException {
        return new Amount(this.service.addAmount(bookId, body.getAmount()));
    }


}
