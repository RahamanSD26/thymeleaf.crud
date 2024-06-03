package com.ThymeleafCrud.thymeleaf.crud.Repository;

import com.ThymeleafCrud.thymeleaf.crud.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {

}
