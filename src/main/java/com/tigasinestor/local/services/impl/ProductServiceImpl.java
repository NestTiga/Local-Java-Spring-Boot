package com.tigasinestor.local.services.impl;

import com.tigasinestor.local.dao.repositories.ProductRepository;
import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.messages.GlobalMessages;
import com.tigasinestor.local.model.entities.Product;
import com.tigasinestor.local.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) throws PresentException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent())
            return product.get();
        else
            throw new PresentException(GlobalMessages.PRODUCT_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
    }

    @Override
    public Product saveProduct(Product product) throws PresentException {
        Optional<Product> findProduct = productRepository.findByCode(product.getCode());
        if (findProduct.isPresent())
            throw new PresentException(GlobalMessages.PRODUCT_CODE_ALREADY_EXISTS.concat(product.getCode()), HttpStatus.BAD_REQUEST);
        else
            return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, Long id) throws PresentException {
        Optional<Product> findProduct = productRepository.findById(id);

        if (findProduct.isPresent()) {
            Product updateProduct = findProduct.get();
            if(updateProduct.getCode().equals(product.getCode())){
                updateProduct.setCode(product.getCode());
                updateProduct.setName(product.getName());
                updateProduct.setDescription(product.getDescription());
                updateProduct.setPrice(product.getPrice());
            }else {
                Optional<Product> findProductByCode = productRepository.findByCode(product.getCode());
                if (findProductByCode.isPresent())
                    throw new PresentException(GlobalMessages.PRODUCT_CODE_ALREADY_EXISTS.concat(product.getCode()), HttpStatus.BAD_REQUEST);
                else {
                    updateProduct.setCode(product.getCode());
                    updateProduct.setName(product.getName());
                    updateProduct.setDescription(product.getDescription());
                    updateProduct.setPrice(product.getPrice());
                }
            }

            return productRepository.save(updateProduct);

        } else {
            throw new PresentException(GlobalMessages.PRODUCT_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteProductById(Long id) throws PresentException {
        if (productRepository.existsById(id))
            productRepository.deleteById(id);
        else
            throw new PresentException(GlobalMessages.PRODUCT_ID_NOT_FOUND.concat(String.valueOf(id)), HttpStatus.NOT_FOUND);
    }
}
