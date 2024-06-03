package com.ThymeleafCrud.thymeleaf.crud.service;

import com.ThymeleafCrud.thymeleaf.crud.Repository.ProductRepository;
import com.ThymeleafCrud.thymeleaf.crud.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) throws Exception {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> updateProduct(String id) throws Exception {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() throws Exception {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(String id) throws Exception {
        productRepository.deleteById(id);
    }

}
