package sk.stuba.fei.uim.oop.assignment3.product.logic;


import sk.stuba.fei.uim.oop.assignment3.exeption.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.exeption.NotFoundException;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request) throws NotFoundException;

    Product getById(long id) throws NotFoundException;

    Product update(long id, ProductEditRequest request) throws NotFoundException;

    void delete(long id) throws NotFoundException;

    int getAmount(long id) throws NotFoundException;

    int addAmount(long id, int increment) throws NotFoundException;

    void decreaseAmountOfProduct(Product p, int r) throws IllegalOperationException, NotFoundException;


}
