package com.ThymeleafCrud.thymeleaf.crud.service;

import com.ThymeleafCrud.thymeleaf.crud.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService{
       Product createProduct(Product product)throws Exception;
       Optional<Product> updateProduct(String id)throws Exception;
       List<Product> getAllProducts()throws Exception;
       void deleteProduct(String id)throws Exception;
}
