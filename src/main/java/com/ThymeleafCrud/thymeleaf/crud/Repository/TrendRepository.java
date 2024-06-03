package com.ThymeleafCrud.thymeleaf.crud.Repository;

import com.ThymeleafCrud.thymeleaf.crud.domain.Trend;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrendRepository extends MongoRepository<Trend,String> {
}
