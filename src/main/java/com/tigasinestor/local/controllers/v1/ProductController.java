package com.tigasinestor.local.controllers.v1;

import com.tigasinestor.local.errors.PresentException;
import com.tigasinestor.local.model.entities.Product;
import com.tigasinestor.local.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("product_controller_v1")
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/findAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @RequestMapping("/findById/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) throws PresentException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) throws PresentException {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.updateProduct(product, id));
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws PresentException{
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
