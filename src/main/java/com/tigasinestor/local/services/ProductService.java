package com.tigasinestor.local.services;

import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.model.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id) throws PresentException;

    Product saveProduct(Product product) throws PresentException;

    Product updateProduct(Product product, Long id) throws PresentException;

    void deleteProductById(Long id) throws PresentException;
}
